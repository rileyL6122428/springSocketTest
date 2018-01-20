package l2k.trivia.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import l2k.trivia.server.config.Constants.HTTP;
import l2k.trivia.server.controllers.response.LeaveRoomResponse;
import l2k.trivia.server.dispatcher.MatchmakingDispatcher;
import l2k.trivia.server.dispatcher.RoomDispatcher;
import l2k.trivia.server.domain.Room;
import l2k.trivia.server.domain.User;
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
	
	@PostMapping(value = HTTP.PathPrefixes.ROOM + HTTP.Endpoints.JOIN)
	public ResponseEntity<Room> joinRoom(
			@RequestAttribute(value=HTTP.RequestAttribute.USER) User user,
			@RequestAttribute(value=HTTP.RequestAttribute.ROOM) Room room) {
		
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
			@RequestAttribute(value=HTTP.RequestAttribute.ROOM) Room room) {
		
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
	
}
