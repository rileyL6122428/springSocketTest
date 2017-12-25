package l2k.trivia.server.controllers;

import java.util.UUID;

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
	
	private UserService userService;
	
	private CookieUtil cookieUtil;
	
	@Autowired
	public AppController(UserService userService, CookieUtil cookieUtil) {
		this.userService = userService;
		this.cookieUtil = cookieUtil;
	}

	@GetMapping(value = "/")
	public ModelAndView enterSite(@CookieValue(value="TRIVIA_SESSION_COOKIE", required=false) String triviaSessionCookie, HttpServletResponse response) {
		UUID sessionId = cookieUtil.cookieValueToUUID(triviaSessionCookie);
		User user = userService.registerUser(sessionId);
		cookieUtil.returnSessionCookie(user.getSessionId(), response);
		return new ModelAndView("index.html");
	}
	
}

