package l2k.trivia.server.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import l2k.trivia.server.config.Constants.HTTP;
import l2k.trivia.server.config.Constants.STOMP;
import l2k.trivia.server.config.Constants.Session;
import l2k.trivia.server.controllers.request.JoinRoomRequest;
import l2k.trivia.server.controllers.wsmessages.MatchmakingStats;
import l2k.trivia.server.domain.Room;
import l2k.trivia.server.services.MatchmakingMessagingTemplate;
import l2k.trivia.server.services.MatchmakingService;


@Controller
public class MatchmakingController {
	
	private MatchmakingMessagingTemplate messagingTemplate;
	private MatchmakingService matchmakingService;
	
	@Autowired
	public MatchmakingController(MatchmakingMessagingTemplate messagingTemplate, MatchmakingService matchmakingService) {
		this.messagingTemplate = messagingTemplate;
		this.matchmakingService = matchmakingService;
	}
	
	@SubscribeMapping(STOMP.Endpoints.MATCHMAKING_SUBSCRIPTION)
	public void subscribeToMatchmaking() {
		messagingTemplate.send(matchmakingService.getMatchmakingStats());
	}
	
	@GetMapping(value = HTTP.Endpoints.MATCHMAKING_STATS)
	public ResponseEntity<MatchmakingStats> getMatchmakingStats() {
		return new ResponseEntity<MatchmakingStats>(matchmakingService.getMatchmakingStats(), HttpStatus.OK);
	}
	
	@PostMapping(value = HTTP.Endpoints.MATCHMAKING_SELECTION)
	public ResponseEntity<Room> joinChatRoom(
			@RequestBody JoinRoomRequest joinRoomRequest, 
			@RequestAttribute(value=Session.ID) UUID sessionId) {
		
		Room joinedRoom = matchmakingService.joinRoom(sessionId, joinRoomRequest.getRoomName());
				
		ResponseEntity<Room> response;
		if(joinedRoom != null) {
			response = new ResponseEntity<Room>(joinedRoom, HttpStatus.OK);
			messagingTemplate.send(matchmakingService.getMatchmakingStats());
		} else {
			response = new ResponseEntity<Room>(joinedRoom, HttpStatus.FORBIDDEN);
		}
		
		return response;
	}

}
