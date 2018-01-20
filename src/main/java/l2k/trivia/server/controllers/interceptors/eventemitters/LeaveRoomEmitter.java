package l2k.trivia.server.controllers.interceptors.eventemitters;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import l2k.trivia.server.config.Constants.HTTP;
import l2k.trivia.server.domain.Room;
import l2k.trivia.server.listeners.LeaveRoomListener;

public class LeaveRoomEmitter extends HandlerInterceptorAdapter {
	
	@Autowired private List<LeaveRoomListener> leaveRoomListeners;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		Room room = (Room)request.getAttribute(HTTP.RequestAttribute.ROOM);
		leaveRoomListeners.forEach((listener) -> listener.fireLeaveRoomEvent(room));
	}
	
}
