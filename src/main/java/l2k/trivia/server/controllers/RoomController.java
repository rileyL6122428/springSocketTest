package l2k.trivia.server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import l2k.trivia.server.config.Constants.HTTP;
import l2k.trivia.server.domain.Room;
import l2k.trivia.server.domain.User;

@Controller
public class RoomController {
	
	@PostMapping(value = HTTP.PathPrefixes.ROOM + HTTP.Endpoints.JOIN)
	public ResponseEntity<Room> joinRoom(
			@RequestAttribute(value=HTTP.RequestAttribute.USER) User user,
			@RequestAttribute(value=HTTP.RequestAttribute.ROOM) Room room) {
		
		ResponseEntity<Room> response;
		
		if(room.addUser(user))
			response = new ResponseEntity<Room>(room, HttpStatus.OK);
		else
			response = new ResponseEntity<Room>(room, HttpStatus.FORBIDDEN);
		
		return response;
	}
	
	@PostMapping(value = HTTP.PathPrefixes.ROOM + HTTP.Endpoints.LEAVE)
	public ResponseEntity<Room> leaveRoom(
			@RequestAttribute(value=HTTP.RequestAttribute.USER) User user,
			@RequestAttribute(value=HTTP.RequestAttribute.ROOM) Room room) {
		
		room.removeUser(user);
		return new ResponseEntity<Room>(room, HttpStatus.OK);
	}
	
}
