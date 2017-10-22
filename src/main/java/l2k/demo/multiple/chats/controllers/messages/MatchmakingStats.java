package l2k.demo.multiple.chats.controllers.messages;

import java.util.Map;

import l2k.demo.multiple.chats.domain.Room;

public class MatchmakingStats {
	
	private Map<String, Room> rooms;
	private int userTotal;
	
	public Map<String, Room> getRooms() {
		return rooms;
	}
	
	public void setRooms(Map<String, Room> rooms) {
		this.rooms = rooms;
	}

	public int getUserTotal() {
		return userTotal;
	}

	public void setUserTotal(int userTotal) {
		this.userTotal = userTotal;
	}
	
}
