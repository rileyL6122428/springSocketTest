package l2k.trivia.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import l2k.trivia.server.domain.User;

@Service
public class MatchmakingService {
	
	@Autowired
	private RoomMonitor roomMonitor;
	
	public boolean userCanJoinRoom(User user, String roomName) {
		return user != null && !roomMonitor.roomIsFull(roomName);
	}
	
}
