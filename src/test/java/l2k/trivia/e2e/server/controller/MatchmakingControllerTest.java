package l2k.trivia.e2e.server.controller;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeoutException;

import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
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
import l2k.trivia.server.controllers.wsmessages.MatchmakingStats;
import l2k.trivia.server.domain.Room;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.CoreMatchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = { App.class })
//@SpringBootTest
//@WebAppConfiguration
@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class MatchmakingControllerTest extends BaseControllerTest {
	
	private static final String MATCHMAKING_STOMP_ENDPOINT = "/topic/matchmaking"; 
	
	@BeforeEach
	public void subscribeToMatchmaking() {
		stompSession.subscribe(getMatchmakingSubscriptionHeaders(), new DefaultStompFrameHandler());		
	}
	
	@Nested
	class GetMatchmakingStats {
		
		@Test
		public void returnsCurrentMatchmakingStats() throws Exception {
			MvcResult result = mockMvc.perform(get("/matchmaking/stats"))
					.andExpect(status().is(200))
					.andReturn();
			
			MatchmakingStats stats = ServerTestUtil.parseJson(result.getResponse().getContentAsString(), MatchmakingStats.class);
			assertThat(1, equalTo(stats.getUserTotal()));
			
			Map<String, Room> rooms = stats.getRooms();
			assertThat(2, equalTo(rooms.size()));
			assertNotNull(rooms.get("ROOM_ONE"));
			assertNotNull(rooms.get("ROOM_TWO"));
		}
		
	}
	
	
	private StompHeaders getMatchmakingSubscriptionHeaders() {
		StompHeaders headers = new StompHeaders();
		headers.add(StompHeaders.DESTINATION, MATCHMAKING_STOMP_ENDPOINT);
		headers.add("testHeader", sessionId.toString());
		return headers;
	}

}
