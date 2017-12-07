package l2k.trivia.server.channelinterceptor;

import java.util.UUID;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

import l2k.trivia.server.domain.User;
import l2k.trivia.server.services.UserService;

public class MyChannelInterceptor extends ChannelInterceptorAdapter {
	
	private UserService userService;
	
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
    	SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(message);
    	populateUserHeader(headerAccessor);

        return message;
    }
    
    private void populateUserHeader(SimpMessageHeaderAccessor headerAccessor) {
    	String messageType = headerAccessor.getHeader("simpMessageType").toString();
    	
    	if(messageType.equals("MESSAGE") || messageType.equals("SUBSCRIBE")) {
    		String sessionIdString = headerAccessor.getFirstNativeHeader("testHeader");
    		UUID sessionId = UUID.fromString(sessionIdString);
    		User user = userService.getUser(sessionId);
    		headerAccessor.setUser(user);
    	}
    }

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
