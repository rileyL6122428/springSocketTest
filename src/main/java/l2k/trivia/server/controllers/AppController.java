package l2k.trivia.server.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import l2k.trivia.server.domain.User;
import l2k.trivia.server.services.CookieUtil;
import l2k.trivia.server.services.UserService;

@Controller
public class AppController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CookieUtil cookieUtil;

	@GetMapping(value = "/")
	public ModelAndView enterSite(@CookieValue(value="TRIVIA_SESSION_COOKIE", required=false) String sessionId, HttpServletResponse response) {
		User user;
		if(!userService.isCurrentUser(sessionId)) 
			user = userService.addNewAnonymousUser();
		else
			user = userService.getUser(sessionId);
		
		cookieUtil.addSessionCookie(user.getSessionId(), response);
		
		return new ModelAndView("index.html");
	}
	
}

