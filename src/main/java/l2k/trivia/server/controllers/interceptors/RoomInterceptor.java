package l2k.trivia.server.controllers.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import static l2k.trivia.server.config.Constants.Session;
import static l2k.trivia.server.config.Constants.HTTP;

import java.util.Map;
import java.util.UUID;

import l2k.trivia.server.config.Constants;
import l2k.trivia.server.domain.Room;
import l2k.trivia.server.domain.User;
import l2k.trivia.server.services.RoomMonitor;
import l2k.trivia.server.services.UserService;

public class RoomInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoomMonitor roomService;
	
	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler) {
		
		User user = getUser(request);
		request.setAttribute(HTTP.RequestAttribute.USER, user);
		
		Room room = getRoom(request);
		request.setAttribute(HTTP.RequestAttribute.ROOM, room);
		
		return user != null && room != null;
	}
	
	private User getUser(HttpServletRequest request) {
		UUID sessionId = (UUID)request.getAttribute(Session.ID);
		return userService.getUser(sessionId);
	}
	
	private Room getRoom(HttpServletRequest request) {
		Map<String, String> pathVariables = (Map<String ,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String roomName = pathVariables.get(HTTP.PathVariables.ROOM_NAME);
		return roomService.getRoom(roomName);
	}
	
}
