package l2k.trivia.server.controllers;

import java.util.UUID;

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
import l2k.trivia.server.services.CookieUtil;
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
	
	@Autowired
	private CookieUtil cookieUtil;
	
	@SubscribeMapping("/matchmaking")
	public void subscribeToMatchmaking() {
		emitMatchmakingStats();
	}
	
	private void emitMatchmakingStats() {
		template.convertAndSend("/topic/matchmaking", matchmakingService.getMatchmakingStats());
	}
	
	@GetMapping(value="/matchmaking/stats")
	public ResponseEntity<MatchmakingStats> getMatchmakingStats() {
		return new ResponseEntity<MatchmakingStats>(matchmakingService.getMatchmakingStats(), HttpStatus.OK);
	}
	
	@PostMapping(value="/join-chat-room")
	public ResponseEntity<Room> joinChatRoom(@RequestBody JoinRoomRequest joinRoomRequest, @CookieValue(value="TRIVIA_SESSION_COOKIE") String sessionCookie) {
		UUID sessionId = cookieUtil.parseSessionCookie(sessionCookie);
		Room joinedRoom  = matchmakingService.joinRoom(sessionId, joinRoomRequest.getRoomName());
		
		ResponseEntity<Room> responseEntity;
		
		if(joinedRoom != null) {
			responseEntity = new ResponseEntity<Room>(joinedRoom, HttpStatus.OK);
			emitMatchmakingStats();
			
		} else {
			responseEntity = new ResponseEntity<Room>(joinedRoom, HttpStatus.FORBIDDEN);
		}
		
		return responseEntity;
	}

}
