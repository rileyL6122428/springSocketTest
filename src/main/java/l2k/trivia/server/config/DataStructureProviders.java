package l2k.trivia.server.config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import l2k.trivia.server.domain.User;

import static l2k.trivia.server.config.Constants.BeanDefinitions.*;

@Configuration
public class DataStructureProviders {
	
	@Bean(name=SESSION_TO_USER_MAP)
	@Scope("prototype")
	public Map<UUID, User> getSessionToUserMap() {
		return new HashMap<UUID, User>();
	}
	
	
}
