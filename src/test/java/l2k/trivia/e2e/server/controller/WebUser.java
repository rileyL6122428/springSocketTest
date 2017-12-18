package l2k.trivia.e2e.server.controller;

import java.lang.reflect.Type;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSession.Subscription;
import static java.util.concurrent.TimeUnit.*;

public class WebUser {
	
	private StompSession stompSession;
	protected BlockingQueue<String> stompMessageQueue = new LinkedBlockingQueue<String>();
	
	private UUID sessionId;
	
	public WebUser(UUID sessionId, StompSession stompSession) {
		this.stompSession = stompSession;
		this.sessionId = sessionId;
	}
	
	public Subscription openStompSubscriptionTo(String destination) {
		StompHeaders headers = new StompHeaders();
		headers.add(StompHeaders.DESTINATION, destination);
		headers.add("testHeader", sessionId.toString());
		
		return stompSession.subscribe(headers, new WebUserStompFrameHandler());
	}
	
	public String getStompMessageFromQueue() {
		try {
			return stompMessageQueue.poll(1, SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void clearStompMessageQueue() {
		stompMessageQueue.clear();
	}
	
	public void disconnectStompSession() {
		stompSession.disconnect();
	}
	
	public UUID getSessionId() {
		return sessionId;
	}
	
	class WebUserStompFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders stompHeaders) {
            return byte[].class;
        }

        @Override
        public void handleFrame(StompHeaders stompHeaders, Object o) {
            stompMessageQueue.offer(new String((byte[]) o));
        }
    }
	
}
