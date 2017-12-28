package l2k.trivia.utils;

import java.util.UUID;

import l2k.trivia.server.domain.User;

public class UserFactory {
	
	public User newUser(String username) {
		return new User() {{
			setName(username);
			setSessionId(UUID.randomUUID());
		}};
	}
	
}
