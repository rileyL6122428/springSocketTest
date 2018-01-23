package l2k.trivia.server.dispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import l2k.trivia.server.domain.Room;
import l2k.trivia.server.listeners.JoinMatchmakingListener;
import l2k.trivia.server.listeners.JoinRoomListener;
import l2k.trivia.server.services.MatchmakingService;

@Component
public class MatchmakingDispatcher implements JoinRoomListener, JoinMatchmakingListener {
	
	@Autowired private MatchmakingService matchmakingService;
	@Autowired private SimpMessagingTemplate messagingTemplate;
	
	@Override
	public void fireJoinRoomEvent(Room room) { //TODO change to leave Matchmaking Event
		dispatchStats();
	}

	@Override
	public void fireJoinMatchmakingEvent() {
		dispatchStats();
	}
	
	private void dispatchStats() {
		messagingTemplate.convertAndSend("/topic/matchmaking", matchmakingService.getMatchmakingStats());
	}

}
