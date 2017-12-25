package l2k.trivia.e2e.server.controller.util;

import static java.util.concurrent.TimeUnit.SECONDS;
import static l2k.trivia.e2e.server.controller.ServerTestUtil.parseJson;
import static l2k.trivia.e2e.server.controller.ServerTestUtil.toJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.lang.reflect.Type;
import java.util.UUID;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import javax.servlet.http.Cookie;

import org.springframework.http.MediaType;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSession.Subscription;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import l2k.trivia.server.controllers.request.JoinRoomRequest;
import l2k.trivia.server.controllers.wsmessages.MatchmakingStats;
import l2k.trivia.server.domain.User;

public class WebUser {
	
	private UUID sessionId;
	private MockMvc mockMvc; 
	private StompSession stompSession;
	protected BlockingDeque<String> stompMessageDeque = new LinkedBlockingDeque<String>();
	
	
	public WebUser(UUID sessionId, StompSession stompSession, MockMvc mockMvc) {
		this.stompSession = stompSession;
		this.sessionId = sessionId;
		this.mockMvc = mockMvc;
	}
	
	public ResultActions requestToJoinRoom(String roomName) throws Exception {
		return mockMvc
					.perform(post("/join-chat-room")
					.cookie(getSessionCookie())
					.contentType(MediaType.APPLICATION_JSON)
					.content(toJson(new JoinRoomRequest(roomName))));
	}
	
	public MatchmakingStats getMatchmakingStats() throws Exception {
		MvcResult result = mockMvc.perform(get("/matchmaking/stats")).andReturn();
		return parseJson(result.getResponse().getContentAsString(), MatchmakingStats.class);
	}
	
	public String getUsername() throws Exception {
		MvcResult getUserResult = mockMvc.perform(get("/user")
				.cookie(getSessionCookie()))
				.andReturn();
		
		User user = parseJson(getUserResult.getResponse().getContentAsString(), User.class);
		
		return user.getName();
	}
	
	public Subscription openStompSubscriptionTo(String destination) {
		StompHeaders headers = new StompHeaders();
		headers.add(StompHeaders.DESTINATION, destination);
		headers.add("testHeader", sessionId.toString());
		
		return stompSession.subscribe(headers, new WebUserStompFrameHandler());
	}
	
	public String getLastStompMessage() {
		try {
			return stompMessageDeque.pollLast(3, SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void disconnectStompSession() {
		stompSession.disconnect();
	}
	
	public UUID getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(UUID sessionId) {
		this.sessionId = sessionId;
	}
	
	public Cookie getSessionCookie() {
		return new Cookie("TRIVIA_SESSION_COOKIE", sessionId.toString());
	}
	
	class WebUserStompFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            return byte[].class;
        }

        @Override
        public void handleFrame(StompHeaders stompHeaders, Object o) {
            stompMessageDeque.offer(new String((byte[]) o));
        }
    }
	
}
