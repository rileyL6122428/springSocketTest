package l2k.demo.multiple.chats.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import l2k.demo.multiple.chats.domain.Room;
import l2k.demo.multiple.chats.messages.JoinRoomRequest;
import l2k.demo.multiple.chats.messages.JoinRoomResponse;
import l2k.demo.multiple.chats.messages.LeaveRoomRequest;
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
	
	@PostMapping(value="/join-chat-room")
	public ResponseEntity<JoinRoomResponse> joinChatRoom(JoinRoomRequest joinRoomRequest) {
		
		JoinRoomResponse joinChatResponse = new JoinRoomResponse();
		joinChatResponse.setRoom(null);
		
		return new ResponseEntity<JoinRoomResponse>(joinChatResponse, HttpStatus.OK);
	}
	
	@MessageMapping("/matchmaking/enter")
	@SendTo("/topic/matchmaking")
	public MatchmakingStats addUserToMatchmakingQueue(JoinRoomRequest joinChatRequest, GenericMessage message) {
		return getMatchmakingStats();
	}
	
	@MessageMapping("/matchmaking/join-room")
	public void joinRoom(JoinRoomRequest joinChatRequest, Principal principal) {
		if(getRoomMonitor().roomIsFull(joinChatRequest.getRoomName()))
			sendJoinRoomFailureResponse(joinChatRequest, principal);
		else 
			sendJoinRoomSuccessResponse(joinChatRequest, principal);
	}
	
	private void sendJoinRoomFailureResponse(JoinRoomRequest joinChatRequest, Principal principal) {
		String roomName = joinChatRequest.getRoomName();
		
		getRoomMonitor().addUserToRoom(roomName, principal);
		
		JoinRoomResponse joinChatResponse = new JoinRoomResponse();
		joinChatResponse.setRoom(null);
		
		template.convertAndSendToUser(principal.getName(), "/queue/matchmaking", joinChatResponse);
	}
	
	private void sendJoinRoomSuccessResponse(JoinRoomRequest joinChatRequest, Principal principal) {
		String roomName = joinChatRequest.getRoomName();
		
		getRoomMonitor().addUserToRoom(roomName, principal);
		
		JoinRoomResponse joinChatResponse = new JoinRoomResponse();
		joinChatResponse.setRoom(getRoomMonitor().getRoom(roomName));
		
		template.convertAndSendToUser(principal.getName(), "/queue/matchmaking", joinChatResponse);
		template.convertAndSend("/topic/matchmaking", getMatchmakingStats());
	}
	
	@MessageMapping("/matchmaking/leave-room")
	public void leaveRoom(LeaveRoomRequest leaveRoomRequest, Principal principal) {
		Room room = leaveRoomRequest.getRoom();
		getRoomMonitor().removeUserFromRoom(room.getName(), principal);
		template.convertAndSend("/topic/matchmaking", getMatchmakingStats());
	}
	
	private MatchmakingStats getMatchmakingStats() {
		MatchmakingStats stats = new MatchmakingStats();
		
		stats.setUserTotal(userService.getTotalUsers());
		stats.setRooms(getRoomMonitor().getRooms());
		
		return stats;
	}

	public RoomMonitor getRoomMonitor() {
		return roomMonitor;
	}

	public void setRoomMonitor(RoomMonitor roomMonitor) {
		this.roomMonitor = roomMonitor;
	}
	
}
