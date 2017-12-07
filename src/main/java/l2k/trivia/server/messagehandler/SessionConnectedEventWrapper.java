package l2k.trivia.server.messagehandler;

import java.util.LinkedList;
import java.util.Map;

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
	
	public String getSessionId() {
		Message<byte[]> message = connectedEvent.getMessage();
		return SimpMessageHeaderAccessor.getSessionId(message.getHeaders());
	}
	
	public Object getCustomHeader(String headerName) {
		Message<byte[]> message = connectedEvent.getMessage();
		MessageHeaders messageHeaders = message.getHeaders();
		
		GenericMessage genericMessage = (GenericMessage)messageHeaders.get(SimpMessageHeaderAccessor.CONNECT_MESSAGE_HEADER);
		MessageHeaders genericMessageHeaders = genericMessage.getHeaders();
		
		Map nativeHeaderMap = (Map)genericMessageHeaders.get("nativeHeaders");
		LinkedList listWrapper = (LinkedList) nativeHeaderMap.get(headerName);
		return listWrapper.getFirst();
	}
	
}
