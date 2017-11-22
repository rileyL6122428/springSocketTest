package l2k.demo.multiple.chats.game;

import java.util.List;

public class TriviaGame {

	private ScoreKeeper scoreKeeper;
	private QuestionManager questionManager;
	private GameMessageEmitter messageEmitter;

	public TriviaGame(ScoreKeeper scoreKeeper, QuestionManager questionManager, GameMessageEmitter messageEmitter) {
		this.scoreKeeper = scoreKeeper;
		this.questionManager = questionManager;
		this.messageEmitter = messageEmitter;
	}

}
