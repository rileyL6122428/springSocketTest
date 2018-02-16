package l2k.trivia.server.wschannelinterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

import l2k.trivia.server.config.Constants.STOMP.MessageHeaders;
import l2k.trivia.server.services.RoomService;

public class RoomPopulator extends ChannelInterceptorAdapter {
	
	@Autowired private RoomService roomMonitor;
	
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		String roomName = SimpMessageHeaderAccessor.getFirstNativeHeader(MessageHeaders.ROOM_NAME, message.getHeaders());

		if(roomName != null) 
			SimpMessageHeaderAccessor.getMutableAccessor(message)
									 .setHeader(MessageHeaders.ROOM, roomMonitor.getRoom(roomName));
			
		return message;
	}
	
}
