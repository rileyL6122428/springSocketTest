package l2k.trivia.server.controllers.interceptors.requestpopulators;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import l2k.trivia.server.config.Constants.HTTP;
import l2k.trivia.server.domain.Room;
import l2k.trivia.server.services.RoomService;

public class RoomPopulator extends HandlerInterceptorAdapter {
	
	@Autowired
	private RoomService roomService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		Room room = getRoom(request);
		request.setAttribute(HTTP.RequestAttribute.ROOM, room);
		return room != null;
	}
	
	private Room getRoom(HttpServletRequest request) {
		Map<String, String> pathVariables = (Map<String ,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String roomName = pathVariables.get(HTTP.PathVariables.ROOM_NAME);
		return roomService.getRoom(roomName);
	}
	
}
