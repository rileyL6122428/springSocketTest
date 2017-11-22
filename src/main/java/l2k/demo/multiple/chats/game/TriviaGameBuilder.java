package l2k.demo.multiple.chats.game;

import java.util.ArrayList;
import java.util.List;

public class TriviaGameBuilder {
	
	private List<Player> players;
	private List<Question> questions;
	private GameMessageEmitter messageEmitter;

	public TriviaGame build() {
		return new TriviaGame(
			new ScoreKeeper(players),
			new QuestionManager(questions),
			messageEmitter
		);
	}
	
	public TriviaGameBuilder setPlayers(List<Player> players) {
		this.players = players;
		return this;
	}

	public TriviaGameBuilder setQuestions(List<Question> questions) {
		this.questions = questions;
		return this;
	}

	public TriviaGameBuilder setMessageEmitter(GameMessageEmitter messageEmitter) {
		this.messageEmitter = messageEmitter;
		return this;
	}

}
