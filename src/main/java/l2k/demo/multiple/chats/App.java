package l2k.demo.multiple.chats;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@SpringBootApplication
@EnableWebSocketMessageBroker
public class App extends AbstractWebSocketMessageBrokerConfigurer {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/matchmaking").setAllowedOrigins("*")
				.setHandshakeHandler(new DefaultHandshakeHandler() {
					private int userIdx;
					
					@Override
					protected Principal determineUser(ServerHttpRequest request, WebSocketHandler webSocketHandler, Map<String, Object> attributes) {
						Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
	                    authorities.add(new SimpleGrantedAuthority("anonymous"));
	                    Principal principal = new AnonymousAuthenticationToken("WebsocketConfiguration", "anonymous" + userIdx++, authorities);
	                    return principal;
					}
				})
				.withSockJS();
	}
	
	public void configureMessageBroker(MessageBrokerRegistry registry) {	
		registry.enableSimpleBroker("/topic", "/queue");
		registry.setApplicationDestinationPrefixes("/app");
	}

}
