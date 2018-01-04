package l2k.trivia.server.services;

import l2k.trivia.game.TriviaGame;
import l2k.trivia.server.controllers.wsmessages.GameMessage;

public class GameCloseMessage extends GameMessage {

	public GameCloseMessage(TriviaGame triviaGame) {
		super(triviaGame);
	}
	
	{
		typeHeader = "FINISHED";
	}

}
