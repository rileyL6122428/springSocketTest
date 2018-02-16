package l2k.trivia.server.dispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import l2k.trivia.game.TriviaGame;
import l2k.trivia.server.listeners.GameListener;

@Component
public class GameDispatcher implements GameListener {

	@Autowired private SimpMessagingTemplate template;
	
	@Override
	public void fireGameUpdate(TriviaGame game) {
		dispatchGame(game);
	}
	
	private void dispatchGame(TriviaGame game) {
		template.convertAndSend("/topic/room/" + game.getRoomName() + "/game", game);
	}
	
}
