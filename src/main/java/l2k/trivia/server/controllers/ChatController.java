package l2k.trivia.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import l2k.trivia.server.config.Constants.HTTP;
import l2k.trivia.server.config.Constants.STOMP;
import static l2k.trivia.server.config.Constants.STOMP.DestinationVariables;
import l2k.trivia.server.controllers.wsmessages.ChatMessageRequest;
import l2k.trivia.server.domain.Room;
import l2k.trivia.server.domain.User;
import l2k.trivia.server.domain.chat.Chat;
import l2k.trivia.server.services.RoomService;

@Controller
public class ChatController {
	
	@Autowired
	private RoomService roomService;
	
	@GetMapping(HTTP.PathPrefixes.ROOM + HTTP.Endpoints.CHAT)
	public ResponseEntity<Chat> getChat(
			@RequestAttribute(value=HTTP.RequestAttribute.USER) User user,
			@RequestAttribute(value=HTTP.RequestAttribute.ROOM) Room room
		) {
		ResponseEntity<Chat> responseEntity;
		
		if(room.contains(user))
			responseEntity = new ResponseEntity<Chat>(room.getChat(), HttpStatus.OK);
		else 
			responseEntity = new ResponseEntity<Chat>(HttpStatus.FORBIDDEN);
		
		return responseEntity;
	}
	
	@MessageMapping(STOMP.PathPrefixes.ROOM + STOMP.Endpoints.SEND)
	public void sendChatMessage(
			ChatMessageRequest chatMessageRequest,
			@Header(STOMP.MessageHeaders.USER) User user,
			@DestinationVariable(DestinationVariables.ROOM_NAME) String roomName
		) {
		Room room = roomService.getRoom(roomName);
		room.addMessage(user, chatMessageRequest.getMessageBody());
	}

}
