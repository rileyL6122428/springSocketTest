package l2k.trivia.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import l2k.trivia.server.controllers.request.JoinRoomRequest;
import l2k.trivia.server.controllers.wsmessages.MatchmakingStats;
import l2k.trivia.server.domain.Room;
import l2k.trivia.server.domain.User;
import l2k.trivia.server.services.MatchmakingService;
import l2k.trivia.server.services.RoomMonitor;
import l2k.trivia.server.services.UserService;

@Controller
public class MatchmakingController {
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoomMonitor roomMonitor;
	
	@Autowired
	private MatchmakingService matchmakingService;
	
	@SubscribeMapping("/matchmaking")
	public void subscribeToMatchmaking() {
		emitMatchmakingStats();
	}
	
	private void emitMatchmakingStats() {
		template.convertAndSend("/topic/matchmaking", matchmakingService.getMatchmakingStats());
	}
	
//	private MatchmakingStats buildMatchmakingStats() {
//		MatchmakingStats stats = new MatchmakingStats();
//		
//		stats.setUserTotal(userService.getTotalUsers());
//		stats.setRooms(roomMonitor.getRooms());
//		
//		return stats;
//	}
	
	@GetMapping(value="/matchmaking/stats")
	public ResponseEntity<MatchmakingStats> getMatchmakingStats() {
		return new ResponseEntity<MatchmakingStats>(matchmakingService.getMatchmakingStats(), HttpStatus.OK);
	}
	
	@PostMapping(value="/join-chat-room")
	public ResponseEntity<Room> joinChatRoom(@RequestBody JoinRoomRequest joinRoomRequest, @CookieValue(value="TRIVIA_SESSION_COOKIE") String sessionId) {
		ResponseEntity<Room> responseEntity;
		User user = userService.getUser(sessionId);
		
		if(matchmakingService.userCanJoinRoom(user, joinRoomRequest.getRoomName())) {
			roomMonitor.addUserToRoom(joinRoomRequest.getRoomName(), user);
			Room targetRoom = roomMonitor.getRoom(joinRoomRequest.getRoomName());
			responseEntity = new ResponseEntity<Room>(targetRoom, HttpStatus.OK);
			emitMatchmakingStats();
			
		} else {
			Room room = null;
			responseEntity = new ResponseEntity<Room>(room, HttpStatus.FORBIDDEN);
		}
		
		return responseEntity;
	}

}
