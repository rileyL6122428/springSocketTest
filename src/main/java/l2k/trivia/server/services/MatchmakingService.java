package l2k.trivia.server.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import l2k.trivia.server.controllers.wsmessages.MatchmakingStats;
import l2k.trivia.server.domain.Room;
import l2k.trivia.server.domain.User;

@Service
public class MatchmakingService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoomService roomMonitor;
	
	public MatchmakingStats getMatchmakingStats() {
		MatchmakingStats stats = new MatchmakingStats();
		
		stats.setUserTotal(userService.getTotalUsers());
		stats.setRooms(roomMonitor.getRooms());
		
		return stats;
	}
	
	public Room joinRoom(UUID sessionId, String roomName) {
		User user = userService.getUser(sessionId);
		Room joinedRoom = null;
		
		if(userCanJoinRoom(user, roomName)) {
			roomMonitor.addUserToRoom(roomName, user);
			joinedRoom = roomMonitor.getRoom(roomName);
		}
		
		return joinedRoom;
	}
	
	public boolean userCanJoinRoom(User user, String roomName) {
		return user != null && !roomMonitor.roomIsFull(roomName);
	}
	
}
