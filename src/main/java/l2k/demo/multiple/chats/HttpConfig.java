package l2k.demo.multiple.chats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import l2k.demo.multiple.chats.interceptor.MyInterceptor;
import l2k.demo.multiple.chats.services.UserService;

@Configuration
public class HttpConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(new MyInterceptor(){{
				setUserService(userService);
			}})
			.addPathPatterns("/trivia");
	}
	
}
