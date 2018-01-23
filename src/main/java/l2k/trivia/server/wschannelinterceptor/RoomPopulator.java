package l2k.trivia.server.wschannelinterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

import l2k.trivia.server.services.RoomMonitor;
import l2k.trivia.server.services.UserService;

public class RoomPopulator extends ChannelInterceptorAdapter {
	
	@Autowired private RoomMonitor roomMonitor;
//	@Autowired private UserService userService;
	
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		
		String roomName = SimpMessageHeaderAccessor.getFirstNativeHeader("roomName", message.getHeaders());

		if(roomName != null)
			SimpMessageHeaderAccessor.getMutableAccessor(message).setHeader("room", roomMonitor.getRoom(roomName));
		
		
		return message;
	}
	
}
