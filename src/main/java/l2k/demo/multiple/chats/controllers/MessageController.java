package l2k.demo.multiple.chats.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Controller;

import l2k.demo.multiple.chats.domain.InboundMessage;
import l2k.demo.multiple.chats.domain.JoinChatRequest;
import l2k.demo.multiple.chats.domain.OutboundMessage;
import l2k.demo.multiple.chats.domain.Room;
import l2k.demo.multiple.chats.domain.User;
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
	
	@MessageMapping("/matchmaking")
	@SendTo("/queue/messages")
	public Map<String, Room> updateMatchmaking(JoinChatRequest joinChatRequest, GenericMessage message) {
		
		//User convertAndSend or convertAndSendToUser to discriminate between sending to all users?
		
		return roomMonitor.getRooms();
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
