package l2k.demo.multiple.chats.controllers;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class RoomController {
	
	@MessageMapping("/room/{roomName}/enter")
	public void enterRoom(@DestinationVariable String roomName, @Header("testHeader") String sessionId) {
		System.out.println(roomName);
		System.out.println(sessionId);
	}
	
}
