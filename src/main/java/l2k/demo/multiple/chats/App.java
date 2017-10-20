package l2k.demo.multiple.chats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import l2k.demo.multiple.chats.channelinterceptor.MyChannelInterceptor;
import l2k.demo.multiple.chats.services.UserService;

@SpringBootApplication
@EnableWebSocketMessageBroker
public class App extends AbstractWebSocketMessageBrokerConfigurer {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Autowired
	private UserService userService;

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/matchmaking").setAllowedOrigins("*").withSockJS();
	}
	
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.setInterceptors(new MyChannelInterceptor(){{
			setUserService(userService);
		}});
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {	
		registry.enableSimpleBroker("/topic", "/queue");
		registry.setApplicationDestinationPrefixes("/app", "/topic");
	}

}
