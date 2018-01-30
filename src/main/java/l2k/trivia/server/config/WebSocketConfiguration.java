package l2k.trivia.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import l2k.trivia.server.wschannelinterceptor.RoomPopulator;
import l2k.trivia.server.wschannelinterceptor.UserPopulator;

import static l2k.trivia.server.config.Constants.*;

@Configuration
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry
			.addEndpoint(STOMP.Endpoints.SESSION_CONNECTION)
			.setAllowedOrigins("*")
			.withSockJS();
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {	
		registry.enableSimpleBroker(STOMP.PathPrefixes.BROKER_TOPIC);
		
		registry.setApplicationDestinationPrefixes(STOMP.PathPrefixes.APP_MESSAGING);
	}
	
	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.setInterceptors(
			getUserPopulator(),
			getRoomPopulator() 
		);
	}
	
	@Bean
	public RoomPopulator getRoomPopulator() {
		return new RoomPopulator();
	}
	
	@Bean
	public UserPopulator getUserPopulator() {
		return new UserPopulator();
	}
}
