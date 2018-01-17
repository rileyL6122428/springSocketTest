package l2k.trivia.server.services;

import java.util.UUID;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Service;

@Service
public class CookieUtil {
	
	public UUID cookieToUUID(Cookie cookie) {
		UUID uuid = null;
		
		if(cookie != null) 
			uuid = cookieValueToUUID(cookie.getValue());
		
		return uuid;
	}
	
	public UUID cookieValueToUUID(String cookieValue) {
		if(cookieValue == null) return null;
		
		UUID sessionId = null;
		
		try {
			sessionId = UUID.fromString(cookieValue);
		} catch(IllegalArgumentException exception) {
			exception.printStackTrace();
		}
		
		return sessionId;
	}
	
}
