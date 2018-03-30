package l2k.trivia.server.config;

import static l2k.trivia.server.config.Constants.USER_NAMES;

import java.util.LinkedList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import l2k.trivia.server.config.Constants.BeanDefinitions;

@Configuration
public class NameProvider {

	@Bean(name=BeanDefinitions.USER_NAMES)
	@Scope("prototype")
	public LinkedList<String> userNames() {
		return new LinkedList<String>(USER_NAMES);
	}
	
}
