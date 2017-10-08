package l2k.demo.multiple.chats;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import l2k.demo.multiple.chats.channelinterceptor.MyChannelInterceptor;
import l2k.demo.multiple.chats.interceptor.MyInterceptor;
import l2k.demo.multiple.chats.services.UserService;

@SpringBootApplication
@EnableWebSocketMessageBroker
public class App extends AbstractWebSocketMessageBrokerConfigurer {
	
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	

	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/matchmaking").setAllowedOrigins("*")
//				.setHandshakeHandler(new DefaultHandshakeHandler() {
//					private int userIdx;
//					
//					@Override
//					protected Principal determineUser(ServerHttpRequest request, WebSocketHandler webSocketHandler, Map<String, Object> attributes) {
//						if(request instanceof ServletServerHttpRequest) {  	
//							ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
////							Cookie[] cookies = servletRequest.get
//						}
//						
//						Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//	                    authorities.add(new SimpleGrantedAuthority("anonymous"));
//	                    Principal principal = new AnonymousAuthenticationToken("WebsocketConfiguration", "anonymous" + userIdx++, authorities);
//	                    return principal;
//					}
//				})
				.withSockJS();
	}
	
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.setInterceptors(new MyChannelInterceptor(){{
			this.setUserService(userService);
		}});
	}
	
	public void configureMessageBroker(MessageBrokerRegistry registry) {	
		registry.enableSimpleBroker("/topic", "/queue");
		registry.setApplicationDestinationPrefixes("/app");
	}

}
