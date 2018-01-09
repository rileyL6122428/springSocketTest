package l2k.trivia.server.controllers;

import static l2k.trivia.server.config.Constants.*;

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
			CookieUtil cookieUtil ) {
		
		this.matchmakingMessagingTemplate = matchmakingMessagingTemplate;
		this.matchmakingService = matchmakingService;
		this.cookieUtil = cookieUtil;
	}
	
	@SubscribeMapping(STOMP.Endpoints.MATCHMAKING_SUBSCRIPTION)
	public void subscribeToMatchmaking() {
		matchmakingMessagingTemplate.send(matchmakingService.getMatchmakingStats());
	}
	
	@GetMapping(value = HTTP.Endpoints.MATCHMAKING_STATS)
	public ResponseEntity<MatchmakingStats> getMatchmakingStats() {
		return new ResponseEntity<MatchmakingStats>(matchmakingService.getMatchmakingStats(), HttpStatus.OK);
	}
	
	@PostMapping(value = HTTP.Endpoints.MATCHMAKING_SELECTION)
	public ResponseEntity<Room> joinChatRoom(
			@RequestBody JoinRoomRequest joinRoomRequest, 
			@CookieValue(value=Cookies.SESSION_ID) String sessionIdString) {
		
		UUID sessionId = cookieUtil.cookieValueToUUID(sessionIdString);
		Room joinedRoom  = matchmakingService.joinRoom(sessionId, joinRoomRequest.getRoomName());
				
		ResponseEntity<Room> response;
		
		if(joinedRoom != null) {
			response = new ResponseEntity<Room>(joinedRoom, HttpStatus.OK);
			matchmakingMessagingTemplate.send(matchmakingService.getMatchmakingStats());
		} else {
			response = new ResponseEntity<Room>(joinedRoom, HttpStatus.FORBIDDEN);
		}
		
		return response;
	}

}
