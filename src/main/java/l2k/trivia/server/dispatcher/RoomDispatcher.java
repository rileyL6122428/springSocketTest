package l2k.trivia.server.dispatcher;

import org.springframework.stereotype.Component;

import l2k.trivia.server.domain.Room;
import l2k.trivia.server.listeners.LeaveRoomListener;

@Component
public class RoomDispatcher implements LeaveRoomListener {
	
	private ChatDispatcher chatDispatcher;
	
	public RoomDispatcher(ChatDispatcher chatDispatcher) {
		this.chatDispatcher = chatDispatcher;
	}
	
	public void dispatchChatUpdate(Room room) {
		chatDispatcher.dispatchChat(room);
	}

	@Override
	public void fireLeaveRoomEvent(Room room) {
		dispatchChatUpdate(room);
	}

}
