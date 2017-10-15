package l2k.demo.multiple.chats.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import l2k.demo.multiple.chats.domain.User;

@Service
public class UserService {

private Map<UUID, User> sessionToUsers;
	
	{
		sessionToUsers = new HashMap<UUID, User>();
	}
	
	public void addUser(User user) {
		sessionToUsers.put(user.getSessionId(), user);
	}
	
	public User getUser(String sessionIdString) {
		UUID sessionID = UUID.fromString(sessionIdString);
		return sessionToUsers.get(sessionID);
	}
	
	public User getUser(UUID sessionId) {
		return sessionToUsers.get(sessionId);
	}
	
	public void removeUser(String sessionId) {
		sessionToUsers.remove(sessionId);
	}

	public int getTotalUsers() {
		return sessionToUsers.size();
	}
	
	
	
}
