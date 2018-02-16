package l2k.trivia.game;

import java.util.HashMap;
import java.util.List;

import l2k.trivia.server.domain.Room;

public class TriviaGameBuilder {
	
	private List<TriviaRound> rounds;
	private Room room;

	public TriviaGame build() {
		return new TriviaGame(
			new HashMap<String, Player>(),
			new RollCall<TriviaRound>(rounds),
			room
		);
	}

	public TriviaGameBuilder setRounds(List<TriviaRound> rounds) {
		this.rounds = rounds;
		return this;
	}
	
	public TriviaGameBuilder setRoom(Room room) {
		this.room = room;
		return this;
	}

}
