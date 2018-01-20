package l2k.trivia.server.listeners;

import l2k.trivia.server.domain.Room;

public interface LeaveRoomListener {
	
	public void fireLeaveRoomEvent(Room room);
	
}
