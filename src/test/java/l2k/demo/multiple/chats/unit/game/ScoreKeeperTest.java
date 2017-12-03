package l2k.demo.multiple.chats.unit.game;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import l2k.demo.multiple.chats.game.Player;
import l2k.demo.multiple.chats.game.ScoreKeeper;
import l2k.demo.multiple.chats.game.ScoreKeeperBuilder;

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
		Map<Player, Integer> scores = scoreKeeper.getScoreMap();
		assertEquals(0, (int)scores.get(sally));
		assertEquals(0, (int)scores.get(tommy));
	}
	
}
