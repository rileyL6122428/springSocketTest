package l2k.trivia.server.dispatcher;

import org.springframework.stereotype.Component;

import l2k.trivia.server.domain.Room;

@Component
public class RoomDispatcher {
	
	private ChatDispatcher chatDispatcher;
	
	public RoomDispatcher(ChatDispatcher chatDispatcher) {
		this.chatDispatcher = chatDispatcher;
	}
	
	public void dispatchChatUpdate(Room room) {
		chatDispatcher.dispatchChat(room);
	}

}
