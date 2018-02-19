package l2k.trivia.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import l2k.trivia.server.controllers.wsmessages.MatchmakingStats;
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
	
}
