package l2k.trivia.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import l2k.trivia.server.domain.Room;

public class TriviaGameBuilder {
	
	private List<TriviaRound> rounds;
	private Room room;

	public TriviaGame build() {
		return new TriviaGame(
			usersToPlayers(),
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
	
	private Map<String, Player> usersToPlayers() {
		Map<String, Player> players = new HashMap<String, Player>();
		for(String name : room.getUsers().keySet()) {
			players.put(name, new Player(name));
		}
		return players;
	}

}
