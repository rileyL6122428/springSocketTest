 package l2k.trivia.server.controllers;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import l2k.trivia.game.Answer;
import l2k.trivia.server.config.Constants.STOMP;
import l2k.trivia.server.domain.Room;
import l2k.trivia.server.domain.User;

@Controller
public class GameController {
	
	@MessageMapping(STOMP.PathPrefixes.GAME + STOMP.Endpoints.SUBMIT_ANSWER)
	public void submitGameAnswer(
			Answer answer,
			@Header(STOMP.MessageHeaders.USER) User user,
			@Header(STOMP.MessageHeaders.ROOM) Room room
		) {
		room.submitTriviaAnswer(user, answer);
	}
	
}
