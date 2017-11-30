package l2k.demo.multiple.chats.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreKeeperBuilder {
	
	private List<Player> players;
	
	public ScoreKeeper build() {
		Map<Player, Integer> playersToScores = new HashMap<Player, Integer>();
		
		players.forEach((player) -> {
			playersToScores.put(player, 0);
		});
		
		return new ScoreKeeper(playersToScores);
	}
	
	public ScoreKeeperBuilder setPlayers(List<Player> players) {
		this.players = players;
		return this;
	}
	
}
