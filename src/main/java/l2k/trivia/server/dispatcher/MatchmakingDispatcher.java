package l2k.trivia.server.dispatcher;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import l2k.trivia.server.domain.Room;
import l2k.trivia.server.listeners.JoinAndLeaveRoomListener;
import l2k.trivia.server.listeners.SessionCreationListener;
import l2k.trivia.server.services.MatchmakingService;

@Component
public class MatchmakingDispatcher implements JoinAndLeaveRoomListener, SessionCreationListener {
	
	@Autowired private MatchmakingService matchmakingService;
	@Autowired private SimpMessagingTemplate messagingTemplate;
	
	@Override
	public void fireJoinRoomEvent(Room room) {
		dispatchStats();
	}

	@Override
	public void fireLeaveRoomEvent(Room room) {
		dispatchStats();
	}
	
	@Override
	public void fireSessionCreatedEvent(UUID sessionId) {
		dispatchStats();
	}
	
	private void dispatchStats() {
		messagingTemplate.convertAndSend("/topic/matchmaking", matchmakingService.getMatchmakingStats());
	}

}
