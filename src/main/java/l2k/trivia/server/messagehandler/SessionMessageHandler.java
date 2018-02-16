package l2k.trivia.server.messagehandler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import l2k.trivia.server.controllers.wsmessages.MatchmakingStats;
import l2k.trivia.server.domain.User;
import l2k.trivia.server.services.MatchmakingMessagingTemplate;
import l2k.trivia.server.services.RoomService;
import l2k.trivia.server.services.UserService;

@Component
public class SessionMessageHandler {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private RoomService roomService;
	
	@Autowired
	private MatchmakingMessagingTemplate matchmakingTemplate;
	
	private Map<String, UUID> sessionMap = new HashMap<>();
	
	
	@EventListener
	public void handleConnected(SessionConnectedEvent connectEvent) {
		SessionConnectedEventWrapper eventWrapper = new SessionConnectedEventWrapper(connectEvent);
		
		String stompSessionId = eventWrapper.getStompSessionId();
		UUID triviaSessionId = eventWrapper.getUserId();
		
		sessionMap.put(stompSessionId, triviaSessionId);
	}
	
	
	
	@EventListener
	public void handleDisonnect(SessionDisconnectEvent event) {
		String stompSessionId = event.getSessionId();
		UUID triviaSessionId = sessionMap.remove(stompSessionId);
		
		User user = userService.removeUser(triviaSessionId);
		roomService.removeUser(user);
		
		MatchmakingStats stats = new MatchmakingStats();
		stats.setUserTotal(userService.getTotalUsers());
		stats.setRooms(roomService.getRooms());
		matchmakingTemplate.send(stats);
	}

}
