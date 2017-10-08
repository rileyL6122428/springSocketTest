package l2k.demo.multiple.chats.messages;

import l2k.demo.multiple.chats.domain.Room;

public class JoinRoomResponse {
	
	public static JoinRoomResponse successResponse(Room room) {
		JoinRoomResponse joinRoomResponse = new JoinRoomResponse();
		joinRoomResponse.setRoom(room);
		return joinRoomResponse;
	}
	
	public static JoinRoomResponse failureResponse() {
		JoinRoomResponse joinRoomResponse = new JoinRoomResponse();
		joinRoomResponse.setRoom(null);
		return joinRoomResponse;
	}
	
	private Room room;

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
}
