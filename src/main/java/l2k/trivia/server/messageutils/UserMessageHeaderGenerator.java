package l2k.trivia.server.messageutils;

import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;

public class UserMessageHeaderGenerator {
	
	public static MessageHeaders createHeaders(String sessionId) {
		SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
		
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        
        return headerAccessor.getMessageHeaders();
	}
	
}
