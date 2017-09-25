package l2k.demo.multiple.chats.domain;

import java.util.List;

public class StatMessage {
	
	private int userCount;
	private List<RoomStats> roomStats;
	
	static class RoomStats {
		
		private int totalUsers;
		private boolean roomIsFull;
		
		public int getTotalUsers() {
			return totalUsers;
		}
		
		public void setTotalUsers(int totalUsers) {
			this.totalUsers = totalUsers;
		}

		public boolean isRoomIsFull() {
			return roomIsFull;
		}

		public void setRoomIsFull(boolean roomIsFull) {
			this.roomIsFull = roomIsFull;
		}
		
	}

	public List<RoomStats> getRoomStats() {
		return roomStats;
	}

	public void setRoomStats(List<RoomStats> roomStats) {
		this.roomStats = roomStats;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	
}
