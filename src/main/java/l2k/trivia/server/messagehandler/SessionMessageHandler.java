package l2k.trivia.server.messagehandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import l2k.trivia.server.controllers.wsmessages.MatchmakingStats;
import l2k.trivia.server.domain.User;
import l2k.trivia.server.messageutils.UserMessageHeaderGenerator;
import l2k.trivia.server.services.RoomMonitor;
import l2k.trivia.server.services.UserService;

@Component
public class SessionMessageHandler {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private RoomMonitor roomMonitor;
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@EventListener
	public void handleConnected(SessionConnectedEvent connectEvent) {
		SessionConnectedEventWrapper eventWrapper = new SessionConnectedEventWrapper(connectEvent);
//		
//		User user = new User();
//		user.setName((String) eventWrapper.getCustomHeader("username"));
//		user.setSessionId(eventWrapper.getSessionId());
//		
//		userService.addUser(user);
//		
//		template.convertAndSendToUser(
//			user.getSessionId(), 
//			"/queue/messages", 
//			roomMonitor.getRooms(), 
//			UserMessageHeaderGenerator.createHeaders(user.getSessionId())
//		);
	}
	
	
	
	@EventListener
	public void handleDisonnect(SessionDisconnectEvent event) {
		String sessionId = event.getSessionId();
		userService.removeUser(sessionId);
		
		MatchmakingStats stats = new MatchmakingStats();
		stats.setUserTotal(userService.getTotalUsers());
		stats.setRooms(roomMonitor.getRooms());
		template.convertAndSend("/topic/matchmaking-stats", stats);
	}

}
