package l2k.trivia.server.config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import l2k.trivia.server.domain.User;

@Configuration
public class DataStructureConfig {
	
	@Bean
	@Scope("prototype")
	public Map<UUID, User> uuidToUser() {
		return new HashMap<UUID, User>();
	}
}
