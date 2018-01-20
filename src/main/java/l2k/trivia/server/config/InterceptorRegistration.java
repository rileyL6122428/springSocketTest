package l2k.trivia.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import l2k.trivia.server.controllers.interceptors.RoomInterceptor;
import l2k.trivia.server.controllers.interceptors.SessionInterceptor;
import static l2k.trivia.server.config.Constants.HTTP;

@Configuration
public class InterceptorRegistration extends WebMvcConfigurerAdapter {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(createSessionInterceptor());
		registry.addInterceptor(createRoomInterceptor()).addPathPatterns(HTTP.PathPrefixes.ROOM + "/**");
	}
	
	@Bean
	public SessionInterceptor createSessionInterceptor() {
		return new SessionInterceptor();
	}
	
	@Bean 
	public RoomInterceptor createRoomInterceptor() { 
		return new RoomInterceptor();
	}
	
}
