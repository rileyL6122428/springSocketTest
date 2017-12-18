package l2k.trivia.server.controllers.request;

public class JoinRoomRequest {
	
	private String roomName;
	
	public JoinRoomRequest() { }
	
	public JoinRoomRequest(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String name) {
		this.roomName = name;
	}
	
}
