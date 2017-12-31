package l2k.trivia.server.controllers.wsmessages;

import l2k.trivia.game.TriviaGame;

public class GameReadyMessage extends GameMessage {
	
	{
		typeHeader = "READY";
	}
	
	public GameReadyMessage(TriviaGame triviaGame) {
		super(triviaGame);
	}

}
