package l2k.trivia.server.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import l2k.trivia.game.Answer;
import l2k.trivia.server.config.Constants.HTTP;
import l2k.trivia.server.config.Constants.STOMP;
import l2k.trivia.server.config.Constants.Session;
import l2k.trivia.server.controllers.request.JoinRoomRequest;
import l2k.trivia.server.controllers.response.LeaveRoomResponse;
import l2k.trivia.server.controllers.wsmessages.ChatMessageRequest;
import l2k.trivia.server.dispatcher.MatchmakingDispatcher;
import l2k.trivia.server.dispatcher.RoomDispatcher;
import l2k.trivia.server.domain.Room;
import l2k.trivia.server.domain.User;
import l2k.trivia.server.domain.chat.Chat;
import l2k.trivia.server.domain.chat.ChatRoomMessage;
import l2k.trivia.server.services.MatchmakingService;
import l2k.trivia.server.services.RoomMonitor;
import l2k.trivia.server.services.UserService;

@Controller
public class RoomController {
	
	@Autowired private RoomMonitor roomMonitor;
	@Autowired private UserService userService;
	@Autowired private RoomDispatcher roomDispatcher;
	@Autowired private MatchmakingService matchmakingService;
	@Autowired private MatchmakingDispatcher matchmakingDispatcher;
	
	@GetMapping(HTTP.PathPrefixes.ROOM)
	public ResponseEntity<Room> getRoom(
			@RequestAttribute(value=HTTP.RequestAttribute.USER) User user,
			@RequestAttribute(value=HTTP.RequestAttribute.ROOM) Room room
			) {
		
		ResponseEntity<Room> responseEntity; 
		
		if(room.contains(user)) {
			responseEntity = new ResponseEntity<Room>(room, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<Room>(HttpStatus.FORBIDDEN);
		}
		
		return responseEntity;
	}
	
	@GetMapping(HTTP.PathPrefixes.ROOM + "/chat")
	public ResponseEntity<Chat> getChat(
			@RequestAttribute(value=HTTP.RequestAttribute.USER) User user,
			@RequestAttribute(value=HTTP.RequestAttribute.ROOM) Room room
			) {
		
		ResponseEntity<Chat> responseEntity; 
		
		if(room.contains(user)) {
			responseEntity = new ResponseEntity<Chat>(room.getChat(), HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<Chat>(HttpStatus.FORBIDDEN);
		}
		
		return responseEntity;
	}
	
	@SubscribeMapping(STOMP.PathPrefixes.ROOM + STOMP.Endpoints.CHAT)
	public void subscribeToChat(@DestinationVariable String roomName, @Header("testHeader") String sessionId) {
		Room room = roomMonitor.getRoom(roomName);
		roomDispatcher.dispatchChatUpdate(room);
	}
	
	@MessageMapping(STOMP.PathPrefixes.ROOM + STOMP.Endpoints.SEND)
	public void sendChatMessage(
			ChatMessageRequest chatMessageRequest,
			@DestinationVariable String roomName, 
			@Header("testHeader") String sessionId
			) {
		
		User user = userService.getUser(sessionId);
		Room room = roomMonitor.getRoom(roomName);
		
		ChatRoomMessage chatRoomMessage = new ChatRoomMessage(user, chatMessageRequest.getMessageBody());
		room.addMessage(chatRoomMessage);
		
		roomDispatcher.dispatchChatUpdate(room);
	}
	
	@PostMapping(value = HTTP.PathPrefixes.ROOM + HTTP.Endpoints.JOIN)
	public ResponseEntity<Room> joinChatRoom(
			@RequestAttribute(value=HTTP.RequestAttribute.USER) User user,
			@RequestAttribute(value=HTTP.RequestAttribute.ROOM) Room room
			) {
				
		ResponseEntity<Room> response;
		if(room.addUser(user)) {
			response = new ResponseEntity<Room>(room, HttpStatus.OK);
		} else {
			response = new ResponseEntity<Room>(room, HttpStatus.FORBIDDEN);
		}
		matchmakingDispatcher.dispatchStats(matchmakingService.getMatchmakingStats());
		return response;
	}
	
	@PostMapping(value = "/room/{roomName}/leave")
	public ResponseEntity<LeaveRoomResponse> leaveRoom(
			@RequestAttribute(value=HTTP.RequestAttribute.USER) User user,
			@RequestAttribute(value=HTTP.RequestAttribute.ROOM) Room room
		) {
		ResponseEntity<LeaveRoomResponse> responseEntity;
		
		if(user != null) {
			room.removeUser(user);
			roomDispatcher.dispatchChatUpdate(room);
			responseEntity = new ResponseEntity<LeaveRoomResponse>(LeaveRoomResponse.successResponse(), HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<LeaveRoomResponse>(LeaveRoomResponse.failureResponse(), HttpStatus.FORBIDDEN);
		}
		
		return responseEntity;
	}
	
	@MessageMapping("/room/{roomName}/submit-answer")
	public void submitGameAnswer(
			Answer answer,
			@DestinationVariable String roomName,
			@Header("testHeader") String sessionId
		) {
		User user = userService.getUser(sessionId);
		roomMonitor.submitGameAnswer(roomName, user, answer);
	}
	
}
