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
	
	public SequenceBuilder addEvents(DelayedEvent...delayedEvents) {
		for(DelayedEvent delayedEvent : delayedEvents) {
			this.delayedEvents.add(delayedEvent);
		}	
		return this;
	}
	
	public SequenceBuilder addRecurringEvents(int times, DelayedEvent...delayedEvents) {
		for(int counter = 0; counter < times; counter++) {
			addEvents(delayedEvents);
		}
		return this;
	}
	
}
