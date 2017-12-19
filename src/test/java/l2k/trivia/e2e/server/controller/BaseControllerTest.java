package l2k.trivia.e2e.server.controller;

import static l2k.trivia.e2e.server.controller.ServerTestUtil.parseJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import l2k.trivia.e2e.server.controller.util.TestDataConfigurer;
import l2k.trivia.e2e.server.controller.util.WebClientFactory;
import l2k.trivia.e2e.server.controller.util.WebUser;
import l2k.trivia.e2e.server.controller.util.WebUserFactory;
import l2k.trivia.server.controllers.wsmessages.MatchmakingStats;
import l2k.trivia.server.domain.Room;

public class BaseControllerTest {

	protected MockMvc mockMvc;
	protected WebSocketStompClient stompClient;
	
	@Autowired
	private WebClientFactory webClientProvider;
	@Autowired
	private WebUserFactory webUserFactory;
	@Autowired
	private TestDataConfigurer testDataConfigurer;
	
	@BeforeEach
	public void setupMockMvc() {
		mockMvc = webClientProvider.newMockMvc();
	}
	
	@BeforeEach
	public void setupStompClient() { 		
		stompClient = webClientProvider.newStompClient();
	}
		
	protected WebUser sendNewUserIntoSite() {
		return webUserFactory.sendNewUserIntoSite(mockMvc, stompClient);
	}
	
	@AfterEach
	public void disconnectUsers() {
		webUserFactory.discardUsers();
	}

	@AfterEach
	public void clearAndResetData() {
		testDataConfigurer.clearAndResetData();
	}
	
	protected void addRoomToApp(Room room) {
		testDataConfigurer.addRoomToApp(room);
	}
	
	
	protected MatchmakingStats getMatchmakingStats() throws Exception {
		MvcResult result = mockMvc.perform(get("/matchmaking/stats")).andReturn();
		return parseJson(result.getResponse().getContentAsString(), MatchmakingStats.class);
	}
}
