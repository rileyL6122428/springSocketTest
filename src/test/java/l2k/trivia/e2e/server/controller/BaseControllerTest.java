package l2k.trivia.e2e.server.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.lang.reflect.Type;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.Cookie;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import static java.util.Arrays.asList;

import static java.util.concurrent.TimeUnit.*;

public class BaseControllerTest {
	
	private static final String WEBSOCKET_URI = "ws://localhost:8090/matchmaking";

	protected UUID sessionId;
	@Autowired
	protected WebApplicationContext webAppContext;
	protected MockMvc mockMvc;
	
	protected BlockingQueue<String> blockingQueue;
	protected WebSocketStompClient stompClient;
	protected StompSession stompSession;

	@BeforeEach
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}
	
	@BeforeEach
	public void setupStompClient() { 
		//This code and other examples of setting up stomp testing found at:
		//http://rafaelhz.github.io/testing-websockets/
		
		blockingQueue = new LinkedBlockingDeque<>();
        stompClient = new WebSocketStompClient(new SockJsClient(
                asList(new WebSocketTransport(new StandardWebSocketClient()))));
	}
	
	@BeforeEach
	public void  setupStompSession() throws InterruptedException, ExecutionException, TimeoutException {
		stompSession = stompClient
                .connect(WEBSOCKET_URI, new StompSessionHandlerAdapter() {})
                .get(1, SECONDS);
	}
	
	@BeforeEach
	public void enterSite() throws Exception {
		MvcResult result = mockMvc.perform(get("/")).andReturn();
		Cookie triviaSessionCookie = result.getResponse().getCookie("TRIVIA_SESSION_COOKIE");
		sessionId = UUID.fromString(triviaSessionCookie.getValue());
	}
	
	class DefaultStompFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            return byte[].class;
        }

        @Override
        public void handleFrame(StompHeaders stompHeaders, Object o) {
            blockingQueue.offer(new String((byte[]) o));
        }
    }
}
