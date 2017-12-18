package l2k.trivia.e2e.server.controller;

import static java.util.Arrays.asList;
import static l2k.trivia.e2e.server.controller.ServerTestUtil.parseJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import l2k.trivia.server.controllers.wsmessages.MatchmakingStats;
import l2k.trivia.server.services.RoomMonitor;
import l2k.trivia.server.services.UserService;

public class BaseControllerTest {

	@Autowired
	protected WebApplicationContext webAppContext;
	protected MockMvc mockMvc;
	protected WebSocketStompClient stompClient;
	@Autowired
	private UserService userService;
	@Autowired
	private RoomMonitor roomMonitor;
	
	private WebUserFactory webUserFactory = new WebUserFactory();

	@BeforeEach
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}
	
	@BeforeEach
	public void setupStompClient() { 		
        stompClient = new WebSocketStompClient(new SockJsClient(
                asList(new WebSocketTransport(new StandardWebSocketClient()))));
	}
	
	@AfterEach
	public void clearAndResetData() {
		userService.clearAndSetup();
		roomMonitor.clearAndSetup();
	}
	
	protected WebUser sendNewUserIntoSite() {
		return webUserFactory.addNewSiteUser(mockMvc, stompClient);
	}
	
	protected MatchmakingStats getMatchmakingStats() throws Exception {
		MvcResult result = mockMvc.perform(get("/matchmaking/stats")).andReturn();
		return parseJson(result.getResponse().getContentAsString(), MatchmakingStats.class);
	}
}
