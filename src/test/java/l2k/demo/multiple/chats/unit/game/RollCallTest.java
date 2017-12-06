package l2k.demo.multiple.chats.unit.game;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import l2k.demo.multiple.chats.game.RollCall;

class RollCallTest {
	
	private RollCall<Integer> rollCall;
	
	@BeforeEach
	public void setup() {
		rollCall = new RollCall<Integer>(asList(1, 2, 3, 4, 5));
	}

	@Test
	public void reportsTheTotalNumberOfItemsInTheRoll() {
		assertEquals(5, rollCall.getTotalItemCount());
	}

}
