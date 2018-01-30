package l2k.trivia.server.wschannelinterceptor;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

import l2k.trivia.server.services.SessionUtil;
import l2k.trivia.server.services.UserService;

public class UserPopulator extends ChannelInterceptorAdapter {
	
	@Autowired private UserService userService;
	@Autowired private SessionUtil sessionUtil;
	
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		UUID sessionId = sessionUtil.extractSessionId(message);

		if(sessionId != null) 
			SimpMessageHeaderAccessor.getMutableAccessor(message)
									 .setHeader("USER", userService.getUser(sessionId));
			
		return message;
	}
	
}
