package l2k.demo.multiple.chats.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import l2k.demo.multiple.chats.domain.User;

@Service
public class UserService {

private Map<String, User> sessionToUsers;
	
	{
		sessionToUsers = new HashMap<String, User>();
	}
	
	public void addUser(User user) {
		sessionToUsers.put(user.getSessionId(), user);
	}
	
	public User getUser(String sessionId) {
		return sessionToUsers.get(sessionId);
	}
	
	public void removeUser(String sessionId) {
		sessionToUsers.remove(sessionId);
	}

	public int getTotalUsers() {
		return sessionToUsers.size();
	}
	
}
