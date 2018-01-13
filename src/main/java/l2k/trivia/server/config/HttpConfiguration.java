package l2k.trivia.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import l2k.trivia.server.controllers.interceptors.SessionInterceptor;

@Configuration
public class HttpConfiguration extends WebMvcConfigurerAdapter {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(createSessionInterceptor());
	}
	
	@Bean
	public SessionInterceptor createSessionInterceptor() {
		return new SessionInterceptor();
	}
	
}
