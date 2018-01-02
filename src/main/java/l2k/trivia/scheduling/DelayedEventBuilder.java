package l2k.trivia.scheduling;

public class DelayedEventBuilder {
	
	private Runnable runnable;
	private int delay;
	
	public DelayedEvent build() {
		return new DelayedEvent(runnable, delay);
	}
	
	public DelayedEventBuilder setDelay(int delay) {
		this.delay = delay;
		return this;
	}
	
	public DelayedEventBuilder setRunnable(Runnable runnable) {
		this.runnable = runnable;
		return this;
	}
	
}
