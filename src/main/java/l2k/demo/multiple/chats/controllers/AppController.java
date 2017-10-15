package l2k.demo.multiple.chats.controllers;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import l2k.demo.multiple.chats.domain.User;
import l2k.demo.multiple.chats.services.NameGenerator;
import l2k.demo.multiple.chats.services.UserService;

@Controller
public class AppController {
	
	static int userNumb = 0;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NameGenerator nameGenerator;
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView registerWithApp(@CookieValue(value="TRIVIA_SESSION_COOKIE", required=false) String sessionId, HttpServletResponse response) {
		if(!isCurrentUser(sessionId)) {
			User user = newAnonymousUser();
			userService.addUser(user);
			addSessionIdToCookies(user, response);			
		} 
		
		return new ModelAndView("trivia-client.html");
	}
	
	private boolean isCurrentUser(String sessionId) {
		return sessionId != null && userService.getUser(sessionId) != null;
	}

	private User newAnonymousUser() {
		User user = new User();
		
		user.setSessionId(UUID.randomUUID());
		user.setName(nameGenerator.getName());
		
		return user;
	}
	
	private void addSessionIdToCookies(User user, HttpServletResponse response) {
		Cookie customSessionCookie = new Cookie("TRIVIA_SESSION_COOKIE", user.getSessionId().toString());
		response.addCookie(customSessionCookie);
	}
}

