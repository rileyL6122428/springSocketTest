package l2k.trivia.unit.server.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
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
		public void returnsAUserWithASessionIDWhenProvidedIdIsNull() {
			User user = userService.registerUser(null);
			assertNotNull(user);
			assertNotNull(user.getSessionId());
		}
		
		@Test
		public void returnsUserWithProvidedSessionIdWhenSessionIdIsAlreadyRegistered() {
			User firstRegisterdUser = userService.registerUser(null);
			UUID sessionId = firstRegisterdUser.getSessionId();
			
			User secondRegisteredUser = userService.registerUser(sessionId);
			assertThat(secondRegisteredUser, equalTo(firstRegisterdUser));
		}
		
		@Test
		public void returnsUserWithNewSessionIdWhenProvidedSessionIdIsNotRegistered() {
			UUID sessionId = UUID.randomUUID();
			User user = userService.registerUser(sessionId);
			assertThat(user.getSessionId(), not(equalTo(sessionId)));
		}
		
	}
	
	@Nested
	class GetUser {
		
		@Test
		public void returnsNullWhenSessionIdOfUnregisteredUserIsProvided() {
			UUID randomSessionId = UUID.randomUUID();
			User user = userService.getUser(randomSessionId);
			assertNull(user);
		}
		
		@Test
		public void returnsUserWhenSessionIDOfRegisteredUserProvided() {
			User user = userService.registerUser(null);
			User retrievedUser = userService.getUser(user.getSessionId());
			assertEquals(user, retrievedUser);
		}
		
	}
	
}
