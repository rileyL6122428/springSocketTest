package l2k.demo.multiple.chats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@SpringBootApplication
@EnableWebSocketMessageBroker
public class App extends AbstractWebSocketMessageBrokerConfigurer {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/matchmaking").setAllowedOrigins("*").withSockJS();
	}
	
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/queue");
		registry.setApplicationDestinationPrefixes("/app");
	}

}
