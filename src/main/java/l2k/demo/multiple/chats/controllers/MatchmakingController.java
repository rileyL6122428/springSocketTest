package l2k.demo.multiple.chats.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import l2k.demo.multiple.chats.controllers.request.JoinRoomRequest;
import l2k.demo.multiple.chats.controllers.response.JoinRoomResponse;
import l2k.demo.multiple.chats.controllers.wsmessages.MatchmakingStats;
import l2k.demo.multiple.chats.domain.Room;
import l2k.demo.multiple.chats.domain.User;
import l2k.demo.multiple.chats.services.RoomMonitor;
import l2k.demo.multiple.chats.services.UserService;

@Controller
public class MatchmakingController {
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoomMonitor roomMonitor;
	
	@SubscribeMapping("/matchmaking")
	public void subscribeToMatchmaking() {
		emitMatchmakingStats();
	}
	
	private void emitMatchmakingStats() {
		MatchmakingStats stats = new MatchmakingStats();
		
		stats.setUserTotal(userService.getTotalUsers());
		stats.setRooms(getRoomMonitor().getRooms());
		
		template.convertAndSend("/topic/matchmaking", stats);
	}
	
	@PostMapping(value="/join-chat-room")
	public ResponseEntity<JoinRoomResponse> joinChatRoom(@RequestBody JoinRoomRequest joinRoomRequest, @CookieValue(value="TRIVIA_SESSION_COOKIE") String sessionId) {
		ResponseEntity<JoinRoomResponse> responseEntity;
		User user = userService.getUser(sessionId);
		
		if(userCanJoinRoom(user, joinRoomRequest)) {
			roomMonitor.addUserToRoom(joinRoomRequest.getRoomName(), user);
			Room targetRoom = roomMonitor.getRoom(joinRoomRequest.getRoomName());
			responseEntity = new ResponseEntity<JoinRoomResponse>(JoinRoomResponse.successResponse(targetRoom), HttpStatus.OK);
			emitMatchmakingStats();
			
		} else {
			responseEntity = new ResponseEntity<JoinRoomResponse>(JoinRoomResponse.failureResponse(), HttpStatus.FORBIDDEN);
		}
		
		return responseEntity;
	}
	
	private boolean userCanJoinRoom(User user, JoinRoomRequest joinRoomRequest) {
		return user != null && !roomMonitor.roomIsFull(joinRoomRequest.getRoomName());
	}
	
	public RoomMonitor getRoomMonitor() {
		return roomMonitor;
	}

	public void setRoomMonitor(RoomMonitor roomMonitor) {
		this.roomMonitor = roomMonitor;
	}
	
}
