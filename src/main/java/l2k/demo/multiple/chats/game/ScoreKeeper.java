package l2k.demo.multiple.chats.game;

import static java.util.Collections.unmodifiableMap;
import java.util.Map;

public class ScoreKeeper {
	
	private Map<Player, Integer> playerToScores; 

	public ScoreKeeper(Map<Player, Integer> playerToScores) {
		this.playerToScores = playerToScores;
	}
	
	public void incrementScore(Player player) {
		Integer previousScore = playerToScores.get(player);
		playerToScores.put(player, previousScore + 1);
	}
	
	public Map<Player, Integer> getScoreMap() {
		return unmodifiableMap(playerToScores);
	}

}
