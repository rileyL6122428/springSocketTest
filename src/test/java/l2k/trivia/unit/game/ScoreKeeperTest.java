package l2k.trivia.unit.game;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import l2k.trivia.game.Player;
import l2k.trivia.game.ScoreKeeper;
import l2k.trivia.game.ScoreKeeperBuilder;

class ScoreKeeperTest {

	private ScoreKeeper scoreKeeper;
	private Player sally;
	private Player tommy;
	
	@BeforeEach
	public void setupCharacters() {
		sally = new Player("Sally");
		tommy = new Player("Tommy");
	}
	
	@BeforeEach
	public void setupScoreKeeper() {
		scoreKeeper = new ScoreKeeperBuilder()
				.setPlayers(asList(sally, tommy))
				.build();
	}
	
	@Test
	public void playerScoresStartAtZero() {
		verifyScore(sally, 0);
		verifyScore(tommy, 0);
	}
	
	@Test
	public void incrementScoresIncreasesAPlayersScoreByOne() {
		for(int times = 0; times < 300; times++) {
			verifyScore(sally, times);
			verifyScore(tommy, 0);
			scoreKeeper.incrementScore(sally);
		}
		
		verifyScore(sally, 300);
		verifyScore(tommy, 0);
		
		for(int times = 0; times < 217; times++) {
			verifyScore(sally, 300);
			verifyScore(tommy, times);
			scoreKeeper.incrementScore(tommy);
		}
		
		verifyScore(sally, 300);
		verifyScore(tommy, 217);
	}
	
	private void verifyScore(Player player, int score) {
		Map<Player, Integer> scores = scoreKeeper.getScoreMap();
		assertEquals(score, (int)scores.get(player));
	}
	
}
