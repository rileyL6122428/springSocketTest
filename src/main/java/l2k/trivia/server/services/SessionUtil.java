package l2k.trivia.server.services;

import java.util.UUID;

import javax.servlet.http.Cookie;

import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;

import l2k.trivia.server.config.Constants.STOMP;

@Service
public class SessionUtil {
	
	public UUID cookieToUUID(Cookie cookie) {
		UUID uuid = null;
		
		if(cookie != null) 
			uuid = stringToUUID(cookie.getValue());
		
		return uuid;
	}
	
	public UUID extractSessionId(Message<?> message) {
		String sessionIdString = SimpMessageHeaderAccessor.getFirstNativeHeader(STOMP.MessageHeaders.SESSION_ID, message.getHeaders());
		return stringToUUID(sessionIdString);
	}
	
	public UUID stringToUUID(String cookieValue) {
		if(cookieValue == null) return null;
		
		UUID sessionId = null;
		
		try {
			sessionId = UUID.fromString(cookieValue);
		} catch(IllegalArgumentException exception) { 
			//TODO
		}
		
		return sessionId;
	}
	
}
