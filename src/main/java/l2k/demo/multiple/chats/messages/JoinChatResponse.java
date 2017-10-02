package l2k.demo.multiple.chats.messages;

public class JoinChatResponse {
	
	private boolean requestSuccessful;
	private String roomName;
	
	public boolean isRequestSuccessful() {
		return requestSuccessful;
	}
	
	public void setRequestSuccessful(boolean requestSuccessful) {
		this.requestSuccessful = requestSuccessful;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
}
