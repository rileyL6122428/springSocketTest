package l2k.trivia.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MatchmakingMessagingTemplate {

	@Autowired
	private SimpMessagingTemplate template;
	
	public void send(Object messagePayload) {
		template.convertAndSend("/topic/matchmaking", messagePayload);
	}
	
}
