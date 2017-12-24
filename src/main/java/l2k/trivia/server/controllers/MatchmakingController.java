package l2k.trivia.server.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import l2k.trivia.server.controllers.request.JoinRoomRequest;
import l2k.trivia.server.controllers.wsmessages.MatchmakingStats;
import l2k.trivia.server.domain.Room;
import l2k.trivia.server.services.CookieUtil;
import l2k.trivia.server.services.MatchmakingMessagingTemplate;
import l2k.trivia.server.services.MatchmakingService;

@Controller
public class MatchmakingController {
	
	private MatchmakingMessagingTemplate matchmakingMessagingTemplate;
	private MatchmakingService matchmakingService;
	private CookieUtil cookieUtil;
	
	@Autowired
	public MatchmakingController(
			MatchmakingMessagingTemplate matchmakingMessagingTemplate,
			MatchmakingService matchmakingService,
			CookieUtil cookieUtil) {
		
		this.matchmakingMessagingTemplate = matchmakingMessagingTemplate;
		this.matchmakingService = matchmakingService;
		this.cookieUtil = cookieUtil;
	}
	
	
	@SubscribeMapping("/matchmaking")
	public void subscribeToMatchmaking() {
		matchmakingMessagingTemplate.send(matchmakingService.getMatchmakingStats());
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
			matchmakingMessagingTemplate.send(matchmakingService.getMatchmakingStats());
			
		} else {
			responseEntity = new ResponseEntity<Room>(joinedRoom, HttpStatus.FORBIDDEN);
		}
		
		return responseEntity;
	}

}
