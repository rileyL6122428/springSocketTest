package l2k.demo.multiple.chats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import l2k.demo.multiple.chats.interceptor.AuthenticationInterceptor;

@Configuration
public class HttpConfiguration extends WebMvcConfigurerAdapter {
	
	@Autowired
	private HandlerInterceptor authenticationInterceptor;
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(authenticationInterceptor)
			.addPathPatterns("/trivia");
	}
	
}
