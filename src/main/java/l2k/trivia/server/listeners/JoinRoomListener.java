package l2k.trivia.server.listeners;

import l2k.trivia.server.domain.Room;

public interface JoinRoomListener {

	public void fireJoinRoomEvent(Room room);
	
}
