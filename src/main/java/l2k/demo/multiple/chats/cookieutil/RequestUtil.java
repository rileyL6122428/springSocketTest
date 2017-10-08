package l2k.demo.multiple.chats.cookieutil;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import l2k.demo.multiple.chats.domain.User;
import l2k.demo.multiple.chats.services.UserService;

@Component
public class RequestUtil {
	
	@Autowired 
	private UserService userService;

	private final String customSessionCookieName = "TRIVIA_SESSION_COOKIE";
	
	public User getUser(String sessionIdString) {
		User user;
		
		try {
			UUID sessionId = UUID.fromString(sessionIdString);
			user = (User) userService.getUser(sessionId);			
		} catch(Exception exception) {
			exception.printStackTrace();
			user = null;
		}
		
		return user;
	}
	
	public User getUser(HttpServletRequest request) {
		User user;
		
		try {
			UUID sessionId = getSessionId(request);
			user = (User) userService.getUser(sessionId);			
		} catch(Exception exception) {
			exception.printStackTrace();
			user = null;
		}
		
		return user;
	}
	
	public UUID getSessionId(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request, customSessionCookieName);
		String sessionIdString = cookie.getValue();
		return UUID.fromString(sessionIdString);
	}
}
