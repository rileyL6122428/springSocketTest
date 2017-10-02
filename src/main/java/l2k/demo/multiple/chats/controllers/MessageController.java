package l2k.demo.multiple.chats.controllers;

import java.security.Principal;

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

import l2k.demo.multiple.chats.domain.User;
import l2k.demo.multiple.chats.messages.EnterMatchmaking;
import l2k.demo.multiple.chats.messages.JoinChatResponse;
import l2k.demo.multiple.chats.messages.MatchmakingStats;
import l2k.demo.multiple.chats.messages.OutboundMessage;
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
		return getMatchmakingStats();
	}
	
	@MessageMapping("/matchmaking/join-room")
//	@SendTo("/queue/matchmaking")
//	@ReplyToUser(value="/queue/errors")
//	public JoinChatResponse joinRoom(EnterMatchmaking joinChatRequest, Principal principal, GenericMessage message) {
	public void joinRoom(EnterMatchmaking joinChatRequest, Principal principal, GenericMessage message) {
		
		JoinChatResponse joinChatResponse = new JoinChatResponse();
		joinChatResponse.setRequestSuccessful(true);
		
		template.convertAndSendToUser(principal.getName(), "/queue/matchmaking", joinChatResponse);
		
//		return joinChatResponse;
	}
	
	private MatchmakingStats getMatchmakingStats() {
		MatchmakingStats stats = new MatchmakingStats();
		
		stats.setUserTotal(userService.getTotalUsers());
		stats.setRooms(roomMonitor.getRooms());
		
		return stats;
	}
	
	
	//	@MessageMapping("/matchmaking")
	//	public void updateMatchmaking(InboundMessage inboundMessage, GenericMessage message) {
	//		SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
	//		
	//		String sessionId = SimpMessageHeaderAccessor.getSessionId(message.getHeaders());
	//		User user = userService.getUser(sessionId);
	//		
	//		System.out.println(message);
	//		OutboundMessage outboundMessage = new OutboundMessage(inboundMessage);
	//		outboundMessage.setTimeStamp("DUMMY TIME STAMP");
	//		template.convertAndSendToUser(user.getSessionId(), "/queue/messages", outboundMessage, createHeaders(user.getSessionId()));
	//	}
	
	private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
	
}
