package l2k.demo.multiple.chats.domain;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

public class Room {
	
	private String name;
	private Map<String, Principal> users;
	private int maxNumberOfUsers;
	
	{
		users = new HashMap<String, Principal>();
	}
	
	public boolean isFull() {
		return users.size() >= maxNumberOfUsers;
	}
	
	public void addUser(Principal user) {
		users.put(user.getName(), user);
	}
	
	public boolean contains(User user) {
		return users.get(user.getName()) != null;
	}
	
	public int getTotalNumberOfUsers() {
		return users.size();
	}
	
	public void removeUser(User user) {
		users.remove(user.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxNumberOfUsers() {
		return maxNumberOfUsers;
	}

	public void setMaxNumberOfUsers(int maxNumberOfUsers) {
		this.maxNumberOfUsers = maxNumberOfUsers;
	}
	
}
