package l2k.trivia.unit.server.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import l2k.trivia.server.domain.User;
import l2k.trivia.server.services.NameGenerator;
import l2k.trivia.server.services.UserService;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	private UserService userService;
	
	@Mock
	private NameGenerator nameGenerator;
	
	private Map<UUID, User> sessionToUsers;
	
	@BeforeEach
	public void setupSessionToUsers() {
		sessionToUsers = new HashMap<UUID, User>();
	}
	
	@BeforeEach
	public void setupUserService() {
		userService = new UserService(nameGenerator, sessionToUsers);		
	}
	
	@Nested
	class RegisterUser {
		
		@Test
		public void returnsAUserWithASessionID() {
			User user = userService.registerUser(null);
			assertNotNull(user);
			assertNotNull(user.getSessionId());
		}
		
		@Disabled
		@Test
		public void returnsUserWithProvidedSessionIdWhenUserIsAlreadyRegistered() {
			User user = userService.registerUser(null);
			UUID sessionId = user.getSessionId();
			
			User secondUser = userService.registerUser(sessionId);
		}
		
	}
	
}
