package l2k.demo.multiple.chats.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import l2k.demo.multiple.chats.domain.ChatRoomMessage;
import l2k.demo.multiple.chats.domain.Moderator;
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
	
	@MessageMapping("/room/{roomName}/enter")
	public void enterRoom(@DestinationVariable String roomName, @Header("testHeader") String sessionId) {
		User user = userService.getUser(sessionId);
		ChatRoomMessage joinRoomMessage = newJoinRoomMessage(user);
		roomMonitor.addMessageToRoom(roomName, joinRoomMessage);
		Room room = roomMonitor.getRoom(roomName);
		template.convertAndSend("/topic/room/" + roomName, room);
	}
	
	private ChatRoomMessage newJoinRoomMessage(User user) {
		ChatRoomMessage joinRoomMessage = new ChatRoomMessage();
		
		joinRoomMessage.setSender(new Moderator());
		joinRoomMessage.setBody(user.getName() + " joins the CHAT!");
		joinRoomMessage.setTimestamp(new Date());
		
		return joinRoomMessage;
	}
	
}
