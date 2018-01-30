package l2k.trivia.server.controllers.interceptors.requestpopulators;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import l2k.trivia.server.services.SessionUtil;

import static l2k.trivia.server.config.Constants.Cookies;
import static l2k.trivia.server.config.Constants.Session;

import java.util.UUID;

public class SessionPopulator extends HandlerInterceptorAdapter {
	
	@Autowired
	private SessionUtil cookieUtil;

	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler) {
		
		Cookie sessionCookie = WebUtils.getCookie(request, Cookies.SESSION_ID);
		UUID sessionId = cookieUtil.cookieToUUID(sessionCookie);
		request.setAttribute(Session.ID, sessionId);
		
		return true;
	}
	
}
