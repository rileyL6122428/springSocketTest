package l2k.trivia.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import l2k.trivia.server.domain.Room;
import l2k.trivia.server.domain.User;

@RunWith(JUnitPlatform.class)
public class RoomTest {

	private Room room;
	
	@BeforeEach
	public void setup() {
		room = new Room();
	}
	
	@AfterEach
	public void tearDown() {
		room = null;
	}
	
	@Test
	@DisplayName(".addUser increments the total number of users by one")
	public void addUserTestOne() {
		Assertions.assertEquals(0, room.getTotalNumberOfUsers());
		
		room.addUser(new User() {{ setName("USER_ONE"); }});
		Assertions.assertEquals(1, room.getTotalNumberOfUsers());
		
		room.addUser(new User() {{ setName("USER_TWO"); }});
		Assertions.assertEquals(2, room.getTotalNumberOfUsers());
	}
	
	@Test
	@DisplayName(".addUser registers a user in the room")
	public void addUserTestTwo() {
		User user = new User() {{ setName("USER_ONE"); }};
		
		Assertions.assertFalse(room.contains(user));
		
		room.addUser(user);
		Assertions.assertTrue(room.contains(user));
	}
	
	@Test
	@DisplayName(".containsUser returns true when a user has been added to the room")
	public void containsUserTestOne() {
		User user = new User() {{ setName("USER_ONE"); }};
		
		room.addUser(user);
		Assertions.assertTrue(room.contains(user));
	}
	
	@Test
	@DisplayName(".containsUser returns false when a user has not been added to the room")
	public void containsUserTestTwo() {
		User user = new User() {{ setName("USER_ONE"); }};
		Assertions.assertFalse(room.contains(user));
	}
	
	@Test
	@DisplayName(".getTotalNumberOfUsers returns the total number of users")
	public void getTotalNumberOfUsersTestOne() {
		User userOne = new User() {{ setName("USER_ONE"); }};
		User userTwo = new User() {{ setName("USER_TWO"); }};
		
		Assertions.assertEquals(0, room.getTotalNumberOfUsers());
		
		room.addUser(userOne);
		Assertions.assertEquals(1, room.getTotalNumberOfUsers());
		
		room.addUser(userOne);
		Assertions.assertEquals(1, room.getTotalNumberOfUsers());
		
		room.addUser(userTwo);
		Assertions.assertEquals(2, room.getTotalNumberOfUsers());
		
		room.removeUser(userTwo);
		Assertions.assertEquals(1, room.getTotalNumberOfUsers());
	}
	
	@Test
	@DisplayName(".removeUser removes a user from the room")
	public void removeUserTestOne() {
		User user = new User() {{ setName("USER_ONE"); }};
		room.addUser(user);
		Assertions.assertTrue(room.contains(user));
		
		room.removeUser(user);
		Assertions.assertFalse(room.contains(user));
	}
	
	@Test
	@DisplayName(".removeUser decrements the total number of users in the room")
	public void removeUserTestTwo() {
		User user = new User() {{ setName("USER_ONE"); }};
		room.addUser(user);
		Assertions.assertEquals(1, room.getTotalNumberOfUsers());
		
		room.removeUser(user);
		Assertions.assertEquals(0, room.getTotalNumberOfUsers());
	}
	
}
