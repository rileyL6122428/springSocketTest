package l2k.demo.multiple.chats.controllers;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
	
	@GetMapping(value = "/")
	public ModelAndView getWelcomeClient() {
		return new ModelAndView("welcome.html");
	}
	
	@PostMapping(value="/join-matchmaking")
	public RedirectView joinMatchmaking(@CookieValue(value="TRIVIA_SESSION_COOKIE", required=false) String sessionId, HttpServletResponse response) {
		if(!isCurrentUser(sessionId)) {
			User user = newAnonymousUser();
			userService.addUser(user);
			addSessionIdToCookies(user, response);			
		} 
		
		return new RedirectView("trivia");
	}
	

	@GetMapping(value = "/trivia")
	public Object getTriviaClient(@CookieValue(value="TRIVIA_SESSION_COOKIE", required=false) String sessionId, HttpServletResponse response) {
		if(!isCurrentUser(sessionId)) {
			return new RedirectView("/");			
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

