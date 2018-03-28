package l2k.trivia.server.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import l2k.trivia.server.domain.User;

import static l2k.trivia.server.config.Constants.BeanDefinitions.SESSION_TO_USER_MAP;

@Service
public class UserService {
	
	private NameGenerator nameGenerator;
	private Map<UUID, User> sessionToUsers;
	
	@Autowired
	private NameRepository nameRepository;
	
	@Autowired
	public UserService(NameGenerator nameGenerator, @Qualifier(value=SESSION_TO_USER_MAP) Map<UUID, User> sessionToUsers) {
		this.nameGenerator = nameGenerator;
		this.sessionToUsers = sessionToUsers;
	}
	
	public void clear() { //USED TEMPORARILY FOR TESTING WHILE DB IS NOT HOOKED UP
		this.sessionToUsers = new HashMap<UUID, User>();
	}
	
	public User registerUser(UUID sessionId) {
		User user;
		
		if(!isCurrentUser(sessionId)) 
			user = addNewAnonymousUser();
		else
			user = getUser(sessionId);
		
		return user;
	}
	
	private User addNewAnonymousUser() {
		User user = new User(nameGenerator.newName());
		user.setSessionId(UUID.randomUUID());		
		sessionToUsers.put(user.getSessionId(), user);
		return user;
	}
	
	private boolean isCurrentUser(UUID sessionId) {
		return sessionId != null && getUser(sessionId) != null;
	}
	
	public User getUser(String sessionIdString) {
		UUID sessionID = UUID.fromString(sessionIdString);
		return sessionToUsers.get(sessionID);
	}
	
	public User getUser(UUID sessionId) {
		return sessionToUsers.get(sessionId);
	}
	
	public User removeUser(UUID sessionId) {
		return sessionToUsers.remove(sessionId);
	}

	public int getTotalUsers() {
		return sessionToUsers.size();
	}

}
