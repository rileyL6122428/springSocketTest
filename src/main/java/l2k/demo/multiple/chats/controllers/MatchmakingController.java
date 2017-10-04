package l2k.demo.multiple.chats.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@PostMapping(value="/test")
	public @ResponseBody Object testing(JoinRoomRequest joinRoomRequest, HttpServletResponse response) {
		response.setStatus(200);
		
		JoinRoomResponse joinChatResponse = new JoinRoomResponse();
		joinChatResponse.setRequestSuccessful(false);
		joinChatResponse.setRoom(null);
		return joinChatResponse;
	}
	
	@MessageMapping("/matchmaking/enter")
	@SendTo("/topic/matchmaking")
	public MatchmakingStats addUserToMatchmakingQueue(JoinRoomRequest joinChatRequest, GenericMessage message) {
		return getMatchmakingStats();
	}
	
	@MessageMapping("/matchmaking/join-room")
	public void joinRoom(JoinRoomRequest joinChatRequest, Principal principal) {
		if(roomMonitor.roomIsFull(joinChatRequest.getRoomName()))
			sendJoinRoomFailureResponse(joinChatRequest, principal);
		else 
			sendJoinRoomSuccessResponse(joinChatRequest, principal);
	}
	
	private void sendJoinRoomFailureResponse(JoinRoomRequest joinChatRequest, Principal principal) {
		String roomName = joinChatRequest.getRoomName();
		
		roomMonitor.addUserToRoom(roomName, principal);
		
		JoinRoomResponse joinChatResponse = new JoinRoomResponse();
		joinChatResponse.setRequestSuccessful(false);
		joinChatResponse.setRoom(null);
		
		template.convertAndSendToUser(principal.getName(), "/queue/matchmaking", joinChatResponse);
	}
	
	private void sendJoinRoomSuccessResponse(JoinRoomRequest joinChatRequest, Principal principal) {
		String roomName = joinChatRequest.getRoomName();
		
		roomMonitor.addUserToRoom(roomName, principal);
		
		JoinRoomResponse joinChatResponse = new JoinRoomResponse();
		joinChatResponse.setRequestSuccessful(true);
		joinChatResponse.setRoom(roomMonitor.getRoom(roomName));
		
		template.convertAndSendToUser(principal.getName(), "/queue/matchmaking", joinChatResponse);
		template.convertAndSend("/topic/matchmaking", getMatchmakingStats());
	}
	
	@MessageMapping("/matchmaking/leave-room")
	public void leaveRoom(LeaveRoomRequest leaveRoomRequest, Principal principal) {
		Room room = leaveRoomRequest.getRoom();
		roomMonitor.removeUserFromRoom(room.getName(), principal);
		template.convertAndSend("/topic/matchmaking", getMatchmakingStats());
	}
	
	private MatchmakingStats getMatchmakingStats() {
		MatchmakingStats stats = new MatchmakingStats();
		
		stats.setUserTotal(userService.getTotalUsers());
		stats.setRooms(roomMonitor.getRooms());
		
		return stats;
	}
	
}
