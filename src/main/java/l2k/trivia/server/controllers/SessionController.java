package l2k.trivia.server.controllers;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import l2k.trivia.server.config.Constants.Cookies;
import l2k.trivia.server.config.Constants.HTTP;
import l2k.trivia.server.config.Constants.Session;
import l2k.trivia.server.domain.User;
import l2k.trivia.server.listeners.SessionCreationListener;
import l2k.trivia.server.services.UserService;

@Controller
public class SessionController {
	
	@Autowired private UserService userService;
	@Autowired private List<SessionCreationListener> sessionCreationListeners;
	
	@PostMapping(value=HTTP.Endpoints.SESSION)
	public void registerSession(
			@RequestAttribute(value=Session.ID, required=false) UUID sessionId,
			HttpServletResponse response
			) {
		
		User user = userService.registerUser(sessionId);
		response.addCookie(new Cookie(Cookies.SESSION_ID, user.getSessionId().toString()));
		sessionCreationListeners.forEach((listener) -> listener.fireSessionCreatedEvent(user.getSessionId()));
	}
	
}
