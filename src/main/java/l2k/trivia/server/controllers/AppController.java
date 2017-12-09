package l2k.trivia.server.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import l2k.trivia.server.domain.User;
import l2k.trivia.server.services.UserService;

@Controller
public class AppController {
	
	@Autowired
	private UserService userService;

	@GetMapping(value = "/")
	public Object getTriviaClient(@CookieValue(value="TRIVIA_SESSION_COOKIE", required=false) String sessionId, HttpServletResponse response) {
		if(!userService.isCurrentUser(sessionId)) {
			User user = userService.addNewAnonymousUser();
			addSessionIdToCookies(user, response);			
		} 
		
		return new ModelAndView("index.html");
	}
	
	private void addSessionIdToCookies(User user, HttpServletResponse response) {
		Cookie customSessionCookie = new Cookie("TRIVIA_SESSION_COOKIE", user.getSessionId().toString());
		response.addCookie(customSessionCookie);
	}
}

