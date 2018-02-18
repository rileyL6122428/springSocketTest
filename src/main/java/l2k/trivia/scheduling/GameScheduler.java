package l2k.trivia.scheduling;

import static l2k.trivia.scheduling.UnitsOfTime.Milliseconds.FIVE_SECONDS;
import static l2k.trivia.scheduling.UnitsOfTime.Milliseconds.THREE_SECONDS;

import l2k.trivia.game.TriviaGame;

public class GameScheduler {
	
	private SequenceBuilder sequenceBuilder;
	
	public GameScheduler schedule(TriviaGame game) {
		initialize();
		scheduleStart(game);	
		scheduleQuestions(game);
		scheduleClose(game);
		return this;
	}
	
	private void initialize() {
		sequenceBuilder = new SequenceBuilder();		
	}
	
	private void scheduleStart(TriviaGame game) {
		sequenceBuilder.addEvent(new DelayedEvent(game::announceStart, THREE_SECONDS));					
	}
	
	private void scheduleQuestions(TriviaGame game) {
		for(int counter = 1; counter <= game.getRoundCount(); counter++) {
			sequenceBuilder.addEvent(new DelayedEvent(game::setupNextRound, THREE_SECONDS));
			sequenceBuilder.addEvent(new DelayedEvent(game::closeCurrentRound, FIVE_SECONDS));			
		}
	}
	
	private void scheduleClose(TriviaGame game) {
		sequenceBuilder.addEvent(new DelayedEvent(game::closeGame, THREE_SECONDS));		
	}
	
	public void execute() {
		sequenceBuilder.build().execute();		
	}
	
}
