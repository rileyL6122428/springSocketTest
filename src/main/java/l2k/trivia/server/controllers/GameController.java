 package l2k.trivia.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import l2k.trivia.game.Answer;
import l2k.trivia.server.config.Constants.STOMP;
import l2k.trivia.server.domain.User;
import l2k.trivia.server.services.RoomMonitor;
import l2k.trivia.server.services.UserService;

@Controller
public class GameController {
	
	@Autowired private RoomMonitor roomMonitor;
	@Autowired private UserService userService;
	
	@MessageMapping(STOMP.PathPrefixes.GAME + STOMP.Endpoints.SUBMIT_ANSWER)
	public void submitGameAnswer(
			Answer answer,
			@DestinationVariable String roomName,
			@Header(STOMP.MessageHeaders.SESSION_ID) String sessionId
		) {
		User user = userService.getUser(sessionId);
		roomMonitor.submitGameAnswer(roomName, user, answer);
	}
	
}
