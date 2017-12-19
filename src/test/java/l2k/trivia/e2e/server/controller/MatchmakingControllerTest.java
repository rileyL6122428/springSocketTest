package l2k.trivia.e2e.server.controller;

import static l2k.trivia.e2e.server.controller.ServerTestUtil.parseJson;
import static l2k.trivia.e2e.server.controller.ServerTestUtil.toJson;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import l2k.trivia.App;
import l2k.trivia.e2e.server.controller.util.WebUser;
import l2k.trivia.server.controllers.request.JoinRoomRequest;
import l2k.trivia.server.controllers.wsmessages.MatchmakingStats;
import l2k.trivia.server.domain.Room;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = { App.class })
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class MatchmakingControllerTest extends BaseControllerTest {
	
	private static final String MATCHMAKING_STOMP_ENDPOINT = "/topic/matchmaking"; 
	
	@BeforeEach
	public void setupRoomOne() {
		 addRoomToApp(new Room() {{
			setName("ROOM_ONE");
			setMaxNumberOfUsers(2);
		}});
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
			assertThat(rooms.size(), equalTo(1));
			assertNotNull(rooms.get("ROOM_ONE"));
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
		
		@Test
		public void returnsForbiddenWhenRoomIsFull() throws Exception {
			WebUser firstUser = sendNewUserIntoSite();
			WebUser secondUser = sendNewUserIntoSite();
			WebUser thirdUser = sendNewUserIntoSite();
			
			joinRoomRequest(firstUser.getSessionId(), "ROOM_ONE")
				.andExpect(status().isOk());
			
			joinRoomRequest(secondUser.getSessionId(), "ROOM_ONE")
				.andExpect(status().isOk());
			
			joinRoomRequest(thirdUser.getSessionId(), "ROOM_ONE")
				.andExpect(status().isForbidden());
		}
		
	}
	
	private ResultActions joinRoomRequest(UUID sessionId, String roomName) throws Exception {
		return mockMvc
					.perform(post("/join-chat-room")
					.cookie(new Cookie("TRIVIA_SESSION_COOKIE", sessionId.toString()))
					.contentType(MediaType.APPLICATION_JSON).content(toJson(new JoinRoomRequest(roomName))));
	}

}
