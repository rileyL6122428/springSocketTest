package l2k.demo.multiple.chats.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import l2k.demo.multiple.chats.controllers.messages.ChatMessageRequest;
import l2k.demo.multiple.chats.controllers.response.LeaveRoomResponse;
import l2k.demo.multiple.chats.domain.ChatRoomMessage;
import l2k.demo.multiple.chats.domain.Room;
import l2k.demo.multiple.chats.domain.User;
import l2k.demo.multiple.chats.services.RoomMonitor;
import l2k.demo.multiple.chats.services.UserService;

@Controller
public class RoomController {
	
	@Autowired
	private RoomMonitor roomMonitor;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@SubscribeMapping("/room/{roomName}")
	public void subscribeToRoom(@DestinationVariable String roomName, @Header("testHeader") String sessionId) {
		Room room = roomMonitor.getRoom(roomName);
		template.convertAndSend("/topic/room/" + roomName, room);
	}
	
	@MessageMapping("/room/{roomName}/send-message")
	public void sendChatMessage(
			ChatMessageRequest sendChatMessageRequest,
			@DestinationVariable String roomName, 
			@Header("testHeader") String sessionId
		) {
		User user = userService.getUser(sessionId);
		ChatRoomMessage chatRoomMessage = new ChatRoomMessage(user, sendChatMessageRequest.getMessageBody());
		roomMonitor.addMessageToRoom(roomName, chatRoomMessage);
		
		template.convertAndSend("/topic/room/" + roomName, roomMonitor.getRoom(roomName));
	}
	
	@PostMapping(value = "/leave-room")
	public ResponseEntity<LeaveRoomResponse> leaveRoom() {
		return null;
	}
	
}
