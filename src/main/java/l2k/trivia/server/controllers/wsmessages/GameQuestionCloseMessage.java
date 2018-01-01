package l2k.trivia.server.controllers.wsmessages;

import l2k.trivia.game.TriviaGame;

public class GameQuestionCloseMessage extends GameMessage {

	public GameQuestionCloseMessage(TriviaGame triviaGame) {
		super(triviaGame);
	}
	
	{
		typeHeader = "QUESTION_CLOSED";
	}

}
