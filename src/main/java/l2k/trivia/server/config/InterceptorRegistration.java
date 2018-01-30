package l2k.trivia.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import l2k.trivia.server.config.Constants.HTTP;
import l2k.trivia.server.controllers.interceptors.requestpopulators.RoomPopulator;
import l2k.trivia.server.controllers.interceptors.requestpopulators.SessionPopulator;

@Configuration
public class InterceptorRegistration extends WebMvcConfigurerAdapter {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(createSessionInterceptor());
		registry.addInterceptor(createRoomInterceptor()).addPathPatterns(HTTP.PathPrefixes.ROOM + "/**");
	}
	
	@Bean
	public SessionPopulator createSessionInterceptor() {
		return new SessionPopulator();
	}
	
	@Bean 
	public RoomPopulator createRoomInterceptor() { 
		return new RoomPopulator();
	}
	
}
