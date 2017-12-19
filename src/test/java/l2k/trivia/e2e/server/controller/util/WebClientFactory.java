package l2k.trivia.e2e.server.controller.util;

import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

@Component
public class WebClientFactory {

	@Autowired
	private WebApplicationContext webAppContext;
	
	public MockMvc newMockMvc() {
		return MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}
	
	public WebSocketStompClient newStompClient() { 		
		return new WebSocketStompClient(new SockJsClient(
				asList(new WebSocketTransport(new StandardWebSocketClient()))));
	}

}
