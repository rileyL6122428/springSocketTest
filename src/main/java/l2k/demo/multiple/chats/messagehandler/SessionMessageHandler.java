package l2k.demo.multiple.chats.messagehandler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import l2k.demo.multiple.chats.domain.User;
import l2k.demo.multiple.chats.services.UserService;

@Component
public class SessionMessageHandler {
	
	@Autowired
	private UserService userService;
	private int userNumber = 0;
	
	@EventListener
	public void handleConnected(SessionConnectedEvent connectEvent) {
//		Message<byte[]> message = event.getMessage();
//		String sessionId = SimpMessageHeaderAccessor.getSessionId(message.getHeaders());
//		SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(message);
//		message.getHeaders().get("simpConnectMessage");
//		GenericMessage genericMessage = (GenericMessage)message.getHeaders().get("simpConnectMessage");
////		headerAccessor.getHeader(headerName)
//		genericMessage.getHeaders().get("nativeHeaders");
		SessionConnectedEventWrapper eventWrapper = new SessionConnectedEventWrapper(connectEvent);
		Map test = (Map)eventWrapper.getCustomHeader("");
		
		
		
		User user = new User();
		user.setName("USER_" + userNumber++);
		user.setSessionId(eventWrapper.getSessionId());
		userService.addUser(user);
		
		System.out.println(connectEvent);
		System.out.println(connectEvent);
	}
	
	@EventListener
	public void handleDisonnect(SessionDisconnectEvent event) {
		// TODO Auto-generated method stub
		System.out.println(event);
		System.out.println(event);
		System.out.println("YOLO");
	}

}
