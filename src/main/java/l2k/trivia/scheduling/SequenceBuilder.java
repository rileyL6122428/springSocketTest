package l2k.trivia.scheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class SequenceBuilder {
	
	private List<DelayedEvent> delayedEvents = new ArrayList<DelayedEvent>();
	
	public Sequence build() {
		return new Sequence(new Timer(), delayedEvents);
	}
	
	public SequenceBuilder addEvent(DelayedEvent delayedEvent) {
		delayedEvents.add(delayedEvent);
		return this;
	}
	
}
