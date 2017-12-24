package l2k.trivia.e2e.server.controller;

import static l2k.trivia.e2e.server.controller.ServerTestUtil.parseJson;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;

import l2k.trivia.App;
import l2k.trivia.e2e.server.controller.util.WebUser;
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
		public void matchmakingStatsEmittedWhenNewUserEntersSite() throws Exception {
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
		
		@Test
		public void matchmakingStatsEmittedWhenUserSuccessfullyJoinsRoom() throws Exception {
			WebUser firstUser = sendNewUserIntoSite();
			firstUser.openStompSubscriptionTo(MATCHMAKING_STOMP_ENDPOINT);
			
			WebUser secondUser = sendNewUserIntoSite();
			
			firstUser.clearStompMessageQueue();
			secondUser.requestToJoinRoom("ROOM_ONE")
				.andExpect(status().isOk());
			
			String matchmakingStatsJson = firstUser.getStompMessageFromQueue();
			MatchmakingStats stats = parseJson(matchmakingStatsJson, MatchmakingStats.class);
			assertThat(stats.getUserTotal(), equalTo(2));
			
			Room roomOne = stats.getRoom("ROOM_ONE");
			assertTrue(roomOne.contains(secondUser.getUsername()));
		}
		
	}
	
	@Nested
	class JoinChatRoom {
		
		@Test
		public void returnsRequestAsForbiddenWhenUserNotRegistered() throws Exception {
			WebUser firstUser = sendNewUserIntoSite();
			firstUser.setSessionId(UUID.randomUUID());
			
			firstUser.requestToJoinRoom("ROOM_ONE")
				.andExpect(status().isForbidden());
		}
		
		@Test
		public void returnsRequestAsForbiddenWhenRoomIsFull() throws Exception {
			WebUser firstUser = sendNewUserIntoSite();
			WebUser secondUser = sendNewUserIntoSite();
			WebUser thirdUser = sendNewUserIntoSite();
			
			firstUser.requestToJoinRoom("ROOM_ONE")
				.andExpect(status().isOk());
			
			secondUser.requestToJoinRoom("ROOM_ONE")
				.andExpect(status().isOk());
			
			thirdUser.requestToJoinRoom("ROOM_ONE")
				.andExpect(status().isForbidden());
		}
		
		@Test
		public void returnsOkStatusWhenRoomIsNotFull() throws Exception {
			WebUser firstUser = sendNewUserIntoSite();
			
			firstUser.requestToJoinRoom("ROOM_ONE")
				.andExpect(status().isOk());
		}
		

		@Test
		public void registersUserInRoomWhenSuccessful() throws Exception {
			WebUser firstUser = sendNewUserIntoSite();
			
			firstUser.requestToJoinRoom("ROOM_ONE");
			
			MatchmakingStats stats = getMatchmakingStats();
			Room roomOne = stats.getRoom("ROOM_ONE");
			assertTrue(roomOne.contains(firstUser.getUsername()));
		}
		
	}

}
