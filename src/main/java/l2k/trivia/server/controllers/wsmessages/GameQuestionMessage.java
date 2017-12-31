package l2k.trivia.server.controllers.wsmessages;

import l2k.trivia.game.TriviaGame;

public class GameQuestionMessage extends GameMessage {

	public GameQuestionMessage(TriviaGame triviaGame) {
		super(triviaGame);
	}
	
	{
		typeHeader = "ASKING_QUESTION";
	}

}
