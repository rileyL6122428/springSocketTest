package l2k.trivia.server.controllers.wsmessages;

import l2k.trivia.game.TriviaGame;

public class GameStartMessage extends GameMessage {
	
	{
		typeHeader = "GAME_START";
	}

	public GameStartMessage(TriviaGame triviaGame) {
		super(triviaGame);
	}

}
