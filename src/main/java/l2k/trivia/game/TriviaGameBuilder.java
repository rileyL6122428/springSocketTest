package l2k.trivia.game;

import java.util.List;

public class TriviaGameBuilder {
	
	private List<Player> players;
	private List<TriviaRound> rounds;

	public TriviaGame build() {
		return new TriviaGame(
			new ScoreKeeperBuilder().setPlayers(players).build(),
			new RollCall<TriviaRound>(rounds)
		);
	}
	
	public TriviaGameBuilder setPlayers(List<Player> players) {
		this.players = players;
		return this;
	}

	public TriviaGameBuilder setRounds(List<TriviaRound> rounds) {
		this.rounds = rounds;
		return this;
	}

}
