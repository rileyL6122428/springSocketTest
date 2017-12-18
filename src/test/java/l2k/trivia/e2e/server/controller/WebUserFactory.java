package l2k.trivia.e2e.server.controller;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.Cookie;

import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class WebUserFactory {
	
	private static String WEBSOCKET_URI = "ws://localhost:8090/matchmaking";
	
	public WebUser addNewSiteUser(MockMvc mockMvc, WebSocketStompClient stompClient) {
		UUID sessionId = enterSite(mockMvc);
		StompSession stompSession = setupStompSession(stompClient);
		
		return new WebUser(sessionId, stompSession);
	}
	
	private UUID enterSite(MockMvc mockMvc) {
		UUID sessionId = null;
		
		try {
			MvcResult result = mockMvc.perform(get("/")).andReturn();
			Cookie sessionCookie = result.getResponse().getCookie("TRIVIA_SESSION_COOKIE");
			sessionId = UUID.fromString(sessionCookie.getValue());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sessionId;
	}
	
	private StompSession setupStompSession(WebSocketStompClient stompClient) {
		StompSession session = null;
		
		try {
			session = stompClient
							.connect(WEBSOCKET_URI, new StompSessionHandlerAdapter() {})
					 	   	.get(1, SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}
		
		return session;
	}
	
}
