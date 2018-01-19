package l2k.trivia.server.dispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import l2k.trivia.server.controllers.wsmessages.MatchmakingStats;

@Component
public class MatchmakingDispatcher {
	
	@Autowired
	private SimpMessagingTemplate template;
	
	public void dispatchStats(MatchmakingStats matchmakingStats) {
		template.convertAndSend("/topic/matchmaking", matchmakingStats);
	}

}
