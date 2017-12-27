package l2k.trivia.server.services;

import l2k.trivia.game.TriviaGame;
import l2k.trivia.server.controllers.wsmessages.GameMessage;
import l2k.trivia.server.controllers.wsmessages.GameReadyMessage;
import l2k.trivia.server.controllers.wsmessages.GameStartMessage;

public class GameMessageFactory {
	
	public GameMessage newGameReadyMessage(TriviaGame triviaGame) {
		return new GameReadyMessage(triviaGame);
	}

	public GameMessage newGameStartMessage(TriviaGame triviaGame) {
		return new GameStartMessage(triviaGame);
	}
	
	
	
}
