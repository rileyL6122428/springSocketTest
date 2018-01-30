package l2k.trivia.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import l2k.trivia.server.config.Constants.HTTP;
import l2k.trivia.server.controllers.interceptors.requestpopulators.RoomPopulator;
import l2k.trivia.server.controllers.interceptors.requestpopulators.SessionPopulator;
import l2k.trivia.server.controllers.interceptors.requestpopulators.UserPopulator;

@Configuration
public class InterceptorRegistration extends WebMvcConfigurerAdapter {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(newSessionPopulator());
		registry.addInterceptor(newUserPopulator()).addPathPatterns(HTTP.Endpoints.USER, HTTP.PathPrefixes.ROOM + "/**");
		registry.addInterceptor(newRoomPopulator()).addPathPatterns(HTTP.PathPrefixes.ROOM + "/**");
	}
	
	@Bean
	public SessionPopulator newSessionPopulator() {
		return new SessionPopulator();
	}

	@Bean
	public UserPopulator newUserPopulator() {
		return new UserPopulator();
	}
	
	@Bean 
	public RoomPopulator newRoomPopulator() { 
		return new RoomPopulator();
	}
	
}
