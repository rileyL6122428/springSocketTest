package l2k.trivia.server.controllers.interceptors.eventemitters;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import l2k.trivia.server.listeners.JoinRoomListener;

public class JoinRoomEmitter extends HandlerInterceptorAdapter {
	
	@Autowired private List<JoinRoomListener> joinRoomListeners;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		joinRoomListeners.forEach(JoinRoomListener::fireJoinRoomEvent);
	}
	
}
