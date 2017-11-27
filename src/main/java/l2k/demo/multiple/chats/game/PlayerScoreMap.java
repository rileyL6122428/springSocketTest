package l2k.demo.multiple.chats.game;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerScoreMap {
	
	private Map<Player, Integer> playerToScores;
	
	PlayerScoreMap(List<Player> players) {
		playerToScores = new HashMap<Player, Integer>();
		players.forEach( (player) -> playerToScores.put(player, 0) );
	}
	
	public Map<Player, Integer> getPlayerScores() {
		return Collections.unmodifiableMap(playerToScores);
	}
	
	void incrementScore(Player player) {
		int previousScore = playerToScores.get(player);
		playerToScores.put(player, previousScore + 1);
	}
	
}
