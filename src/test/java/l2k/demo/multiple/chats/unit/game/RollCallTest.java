package l2k.demo.multiple.chats.unit.game;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
	
	@Test
	public void reportsTheTotalNumberOfItemsObtainedFromTheRoll() {
		assertEquals(0, rollCall.getRetrievedItemCount());
		
		rollCall.getNextItem();
		assertEquals(1, rollCall.getRetrievedItemCount());
		
		rollCall.getNextItem();
		assertEquals(2, rollCall.getRetrievedItemCount());
		
		rollCall.getNextItem();
		assertEquals(3, rollCall.getRetrievedItemCount());
		
		rollCall.getNextItem();
		assertEquals(4, rollCall.getRetrievedItemCount());
		
		rollCall.getNextItem();
		assertEquals(5, rollCall.getRetrievedItemCount());
	}
	
	@Test
	public void reportsTheRollAsFinishedWhenAllItemsRetrieved() {
		assertFalse(rollCall.isFinished());
		
		rollCall.getNextItem();
		assertFalse(rollCall.isFinished());
		
		rollCall.getNextItem();
		assertFalse(rollCall.isFinished());
		
		rollCall.getNextItem();
		assertFalse(rollCall.isFinished());
		
		rollCall.getNextItem();
		assertFalse(rollCall.isFinished());
		
		rollCall.getNextItem();
		assertTrue(rollCall.isFinished());
	}
	
	@Test
	public void returnsTheItemsInTheOrderProvided() {
		assertEquals(1, (int)rollCall.getNextItem());
		assertEquals(2, (int)rollCall.getNextItem());
		assertEquals(3, (int)rollCall.getNextItem());
		assertEquals(4, (int)rollCall.getNextItem());
		assertEquals(5, (int)rollCall.getNextItem());
	}

}
