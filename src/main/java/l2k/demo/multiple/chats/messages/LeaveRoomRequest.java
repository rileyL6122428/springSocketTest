package l2k.demo.multiple.chats.messages;

import l2k.demo.multiple.chats.domain.Room;

public class LeaveRoomRequest {
	
	private Room room;

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
}
