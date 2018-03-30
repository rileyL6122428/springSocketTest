package l2k.trivia.unit.game;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import l2k.trivia.game.RollCall;

class RollCallTest {
	
	private RollCall<Integer> rollCall;
	
	@BeforeEach
	public void setup() {
		rollCall = new RollCall<Integer>(asList(1, 2, 3, 4, 5));
	}

	@Test
	public void reportsTheTotalNumberOfItemsInTheRoll() {
		assertEquals(5, rollCall.getItemTotal());
	}
	
	@Test
	public void reportsTheTotalNumberOfItemsObtainedFromTheRoll() {
		assertEquals(0, rollCall.getCurrentItemNumber());
		
		rollCall.getNextItem();
		assertEquals(1, rollCall.getCurrentItemNumber());
		
		rollCall.getNextItem();
		assertEquals(2, rollCall.getCurrentItemNumber());
		
		rollCall.getNextItem();
		assertEquals(3, rollCall.getCurrentItemNumber());
		
		rollCall.getNextItem();
		assertEquals(4, rollCall.getCurrentItemNumber());
		
		rollCall.getNextItem();
		assertEquals(5, rollCall.getCurrentItemNumber());
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
	
	@Test
	public void returnsNullOnceAallItemsHaveBeenObtained() {
		for(int count = 0; count <= 5; count++) {
			rollCall.getNextItem();			
		}
		
		assertNull(rollCall.getNextItem());
	}

}
