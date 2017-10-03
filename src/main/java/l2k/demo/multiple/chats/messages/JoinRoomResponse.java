package l2k.demo.multiple.chats.messages;

import l2k.demo.multiple.chats.domain.Room;

public class JoinRoomResponse {
	
	private boolean requestSuccessful;
	private Room room;
	
	public boolean isRequestSuccessful() {
		return requestSuccessful;
	}
	
	public void setRequestSuccessful(boolean requestSuccessful) {
		this.requestSuccessful = requestSuccessful;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
}
