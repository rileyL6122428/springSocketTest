package l2k.trivia.server.services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class CookieUtil {
	
	public void addSessionCookie(Object sessionId, HttpServletResponse response) {
		Cookie sessionCookie = new Cookie("TRIVIA_SESSION_COOKIE", sessionId.toString());
		response.addCookie(sessionCookie);
	}
	
}
