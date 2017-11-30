package l2k.demo.multiple.chats.game;

import java.util.List;

public class TriviaGameBuilder {
	
	private List<Player> players;
	private List<Question> questions;

	public TriviaGame build() {
		return new TriviaGame(
			new ScoreKeeperBuilder().setPlayers(players).build(),
			new QuestionRoll(questions)
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

}
