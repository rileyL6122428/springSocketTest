package l2k.trivia.server.services;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class CookieUtil {
	
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
	
	public void returnSessionCookie(Object sessionId, HttpServletResponse response) {
		Cookie sessionCookie = new Cookie("TRIVIA_SESSION_COOKIE", sessionId.toString());
		response.addCookie(sessionCookie);
	}
	
}
