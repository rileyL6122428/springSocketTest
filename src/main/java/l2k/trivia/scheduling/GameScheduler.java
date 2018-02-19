package l2k.trivia.scheduling;

import static l2k.trivia.scheduling.UnitsOfTime.Milliseconds.FIVE_SECONDS;
import static l2k.trivia.scheduling.UnitsOfTime.Milliseconds.THREE_SECONDS;

import l2k.trivia.game.TriviaGame;

public class GameScheduler {
	
	private SequenceBuilder sequenceBuilder;
	
	public GameScheduler schedule(TriviaGame game) {
		sequenceBuilder = new SequenceBuilder()
				.addEvent(new DelayedEvent(game::announceStart, THREE_SECONDS))
				.addRecurringEvents(game.getRoundCount(), 
						new DelayedEvent(game::setupNextRound, THREE_SECONDS),
						new DelayedEvent(game::closeCurrentRound, FIVE_SECONDS))
				.addEvent(new DelayedEvent(game::closeGame, THREE_SECONDS))
				.addEvent(new DelayedEvent(game::emitReadyForCleanUp, THREE_SECONDS));
		return this;
	}
	
	public void execute() {
		sequenceBuilder.build().execute();		
	}
	
}
