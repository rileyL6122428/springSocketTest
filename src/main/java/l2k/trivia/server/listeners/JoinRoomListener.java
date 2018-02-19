package l2k.trivia.server.listeners;

import l2k.trivia.server.domain.Room;
import l2k.trivia.server.domain.User;

public interface JoinRoomListener {

	public void fireJoinRoomEvent(User user, Room room);
	
}
