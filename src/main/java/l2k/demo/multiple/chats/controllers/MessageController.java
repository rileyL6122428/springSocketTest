package l2k.demo.multiple.chats.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Controller;

import l2k.demo.multiple.chats.messages.EnterMatchmaking;
import l2k.demo.multiple.chats.messages.MatchmakingStats;
import l2k.demo.multiple.chats.services.RoomMonitor;
import l2k.demo.multiple.chats.services.UserService;

@Controller
public class MessageController {
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private SimpMessageSendingOperations templateTwo;
	
	@Autowired
	private RoomMonitor roomMonitor;
	
	@MessageMapping("/matchmaking/enter")
	@SendTo("/topic/matchmaking-stats")
	public MatchmakingStats addUserToMatchmakingQueue(EnterMatchmaking joinChatRequest, GenericMessage message) {
		MatchmakingStats stats = new MatchmakingStats();
		
		stats.setUserTotal(userService.getTotalUsers());
		stats.setRooms(roomMonitor.getRooms());
		
		return stats;
	}
	
	
	private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
	
}
