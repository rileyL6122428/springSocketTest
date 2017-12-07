package l2k.trivia.server.controllers.wsmessages;

import java.util.Map;

import l2k.trivia.server.domain.Room;

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
