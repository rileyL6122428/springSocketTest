package l2k.trivia.server.config;

import static l2k.trivia.server.config.Constants.USER_NAMES;
import static l2k.trivia.server.config.Constants.USER_NAMES_BEAN;

import java.util.LinkedList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class NameProvider {

	@Bean(name=USER_NAMES_BEAN)
	@Scope("prototype")
	public LinkedList<String> userNames() {
		return new LinkedList<String>(USER_NAMES);
	}
	
}
