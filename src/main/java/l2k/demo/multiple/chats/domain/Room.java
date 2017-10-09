package l2k.demo.multiple.chats.domain;

import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Room {
	
	private static final int MAXIMUM_NUMBER_OF_PERSISTED_MESSAGES = 75;
	
	private String name;
	private Map<String, Principal> users;
	private int maxNumberOfUsers;
	
	private LinkedList<ChatRoomMessage> messages;
	
	{
		users = new HashMap<String, Principal>();
		messages = new LinkedList<ChatRoomMessage>();
	}
	
	public boolean isFull() {
		return users.size() >= maxNumberOfUsers;
	}
	
	public void addMessage(ChatRoomMessage message) {
		if(messages.size() >= MAXIMUM_NUMBER_OF_PERSISTED_MESSAGES) {
			messages.removeFirst();
		}
		
		messages.addLast(message);
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
	
	public void removeUser(Principal user) {
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
