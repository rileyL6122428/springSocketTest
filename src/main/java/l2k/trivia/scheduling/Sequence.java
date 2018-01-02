package l2k.trivia.scheduling;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Sequence {
	
	private Timer timer;
	private List<DelayedEvent> events;
	
	public Sequence(Timer timer, List<DelayedEvent> events) {
		this.timer = timer;
		this.events = events;
	}
	
	public void execute() {
		int aggregateDelay = 0;
		
		for(DelayedEvent event: events) {
			aggregateDelay += event.getDelay();
			scheduleTask(event.getRunnable(), aggregateDelay);
		}
	}
	
	private void scheduleTask(Runnable runnable, int delay) {
		timer.schedule(new TimerTask() {

			public void run() {
				runnable.run();
			}
			
		}, delay);
	}
	
}
