package l2k.trivia.server.controllers.interceptors.requestpopulators;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import l2k.trivia.server.config.Constants.HTTP;
import l2k.trivia.server.config.Constants.Session;
import l2k.trivia.server.domain.User;
import l2k.trivia.server.services.UserService;

public class UserPopulator extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		User user = getUser(request);
		request.setAttribute(HTTP.RequestAttribute.USER, user);	
		return user != null;
	}
	
	private User getUser(HttpServletRequest request) {
		UUID sessionId = (UUID)request.getAttribute(Session.ID);
		return userService.getUser(sessionId);
	}
	
}
