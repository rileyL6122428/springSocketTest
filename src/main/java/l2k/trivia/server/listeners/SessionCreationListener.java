package l2k.trivia.server.listeners;

import java.util.UUID;

public interface SessionCreationListener {
	
	public void fireSessionCreatedEvent(UUID sessionId);
	
}
