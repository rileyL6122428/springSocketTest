package l2k.trivia.server.messagehandler;

import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

public class SessionConnectedEventWrapper {
	
	private SessionConnectedEvent connectedEvent;
	
	public SessionConnectedEventWrapper(SessionConnectedEvent connectedEvent) {
		this.connectedEvent = connectedEvent;
	}
	
	public String getStompSessionId() {
		return SimpMessageHeaderAccessor.getSessionId(getConnectionHeaders());
	}
	
	public UUID getUserId() {
		String userIdString = getCustomHeader("testHeader");
		return UUID.fromString(userIdString);
	}
	
	private String getCustomHeader(String headerName) {
		MessageHeaders messageHeaders = getConnectionHeaders();
		
		GenericMessage genericMessage = (GenericMessage)messageHeaders.get(SimpMessageHeaderAccessor.CONNECT_MESSAGE_HEADER);
		MessageHeaders genericMessageHeaders = genericMessage.getHeaders();
		
		Map nativeHeaderMap = (Map)genericMessageHeaders.get("nativeHeaders");
		LinkedList<String> listWrapper = (LinkedList<String>) nativeHeaderMap.get(headerName);
		return listWrapper.getFirst();
	}
	
	private MessageHeaders getConnectionHeaders() {
		Message<byte[]> message = connectedEvent.getMessage();
		return message.getHeaders();
	}
	
}
