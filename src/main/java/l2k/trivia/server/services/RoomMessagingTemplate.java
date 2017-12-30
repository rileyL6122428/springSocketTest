package l2k.trivia.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class RoomMessagingTemplate {
	
	@Autowired
	private SimpMessagingTemplate template;
	
	public void sendMessageToRoom(String roomName, Object payload) {
		template.convertAndSend("/topic/room/" + roomName, payload);
	}
	
	public void sendGameMessageToRoom(String roomName, Object payload) {
		template.convertAndSend("/topic/room/" + roomName + "/game", payload);
	}
	
}
