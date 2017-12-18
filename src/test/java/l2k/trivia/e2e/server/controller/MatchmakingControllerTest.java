package l2k.trivia.e2e.server.controller;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.Cookie;

import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import java.lang.reflect.Type;

import l2k.trivia.App;
import l2k.trivia.server.controllers.request.JoinRoomRequest;
import l2k.trivia.server.controllers.wsmessages.MatchmakingStats;
import l2k.trivia.server.domain.Room;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.hamcrest.CoreMatchers.equalTo;
import static l2k.trivia.e2e.server.controller.ServerTestUtil.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = { App.class })
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class MatchmakingControllerTest extends BaseControllerTest {
	
	private static final String MATCHMAKING_STOMP_ENDPOINT = "/topic/matchmaking"; 
	
	private Set<WebUser> users;
	
	@BeforeEach
	public void setupUsersSet() {
		users = new HashSet<WebUser>();
	}
	
	@AfterEach
	public void disconnectUsers() {
		users.forEach(WebUser::disconnectStompSession);
	}
	
	@Nested
	class GetMatchmakingStats {
		
		@Test
		public void returnsCurrentMatchmakingStats() throws Exception {
			sendNewUserIntoSite();
			
			MvcResult result = mockMvc.perform(get("/matchmaking/stats"))
					.andExpect(status().is(200))
					.andReturn();
			
			MatchmakingStats stats = parseJson(result.getResponse().getContentAsString(), MatchmakingStats.class);
			assertThat(1, equalTo(stats.getUserTotal()));
			
			Map<String, Room> rooms = stats.getRooms();
			assertThat(2, equalTo(rooms.size()));
			assertNotNull(rooms.get("ROOM_ONE"));
			assertNotNull(rooms.get("ROOM_TWO"));
		}
		
	}
	
	@Nested
	class SubscribeToMatchmaking {
		
		@Test
		public void emitsMatchmakingStatsWhenNewUserEntersSiteAndSubscribesToMatchmaking() throws Exception {
			WebUser firstUser = sendNewUserIntoSite();
			firstUser.openStompSubscriptionTo(MATCHMAKING_STOMP_ENDPOINT);
			firstUser.clearStompMessageQueue();
			
			WebUser secondUser = sendNewUserIntoSite();
			
			String matchmakingStatsJson = firstUser.getStompMessageFromQueue();
			MatchmakingStats stats = parseJson(matchmakingStatsJson, MatchmakingStats.class);
			assertThat(stats.getUserTotal(), equalTo(2));
			
			Map<String, Room> rooms = stats.getRooms();
			assertThat(2, equalTo(rooms.size()));
			assertNotNull(rooms.get("ROOM_ONE"));
			assertNotNull(rooms.get("ROOM_TWO"));
		}
		
	}
	
	@Nested
	class JoinChatRoom {
		
		@Test
		public void returnsRequestForbiddenWhenUserNotRegistered() throws Exception {
			JoinRoomRequest joinRoomRequest = new JoinRoomRequest();
			joinRoomRequest.setRoomName("ROOM_ONE");
			
			Cookie triviaSession = new Cookie("TRIVIA_SESSION_COOKIE", UUID.randomUUID().toString());
			
			mockMvc
				.perform(post("/join-chat-room")
				.cookie(triviaSession)
				.contentType(MediaType.APPLICATION_JSON).content(toJson(joinRoomRequest)))
				
				.andExpect(status().isForbidden());
		}
		
		@Disabled
		@Test
		public void returnsForbiddentWhenRoomIsFull() {
			
		}
		
	}

}
