package l2k.demo.multiple.chats.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import l2k.demo.multiple.chats.cookieutil.RequestUtil;
import l2k.demo.multiple.chats.domain.Room;
import l2k.demo.multiple.chats.domain.User;
import l2k.demo.multiple.chats.messages.JoinRoomRequest;
import l2k.demo.multiple.chats.messages.JoinRoomResponse;
import l2k.demo.multiple.chats.messages.MatchmakingStats;
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
	public void subscribeToRoom() {
		template.convertAndSend("/topic/matchmaking", getMatchmakingStats());
	}
	
	private MatchmakingStats getMatchmakingStats() {
		MatchmakingStats stats = new MatchmakingStats();
		
		stats.setUserTotal(userService.getTotalUsers());
		stats.setRooms(getRoomMonitor().getRooms());
		
		return stats;
	}
	
	@PostMapping(value="/join-chat-room")
	public ResponseEntity<JoinRoomResponse> joinChatRoom(@RequestBody JoinRoomRequest joinRoomRequest, @CookieValue(value="TRIVIA_SESSION_COOKIE") String sessionId) {
		ResponseEntity<JoinRoomResponse> responseEntity;
		User user = userService.getUser(sessionId);
		
		if(userCanJoinRoom(user, joinRoomRequest)) {
			roomMonitor.addUserToRoom(joinRoomRequest.getRoomName(), user);
			Room targetRoom = roomMonitor.getRoom(joinRoomRequest.getRoomName());
			responseEntity = new ResponseEntity<JoinRoomResponse>(JoinRoomResponse.successResponse(targetRoom), HttpStatus.OK);
			
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
