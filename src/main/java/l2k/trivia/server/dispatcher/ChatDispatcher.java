package l2k.trivia.server.dispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import l2k.trivia.server.domain.Room;
import l2k.trivia.server.listeners.JoinRoomListener;

@Component
public class ChatDispatcher implements JoinRoomListener {
	
	@Autowired
	private SimpMessagingTemplate template;
	
	public void dispatchChat(Room room) {
		template.convertAndSend("/topic/room/" + room.getName() + "/chat", room.getChat());
	}

	@Override
	public void fireJoinRoomEvent(Room room) {
		dispatchChat(room);
	}
	
}
