package l2k.trivia.server.domain;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Room {
	
	private static final int MAXIMUM_NUMBER_OF_PERSISTED_MESSAGES = 75;
	
	private String name;
	private Map<String, User> users;
	private int maxNumberOfUsers;
	private LinkedList<ChatRoomMessage> messages;
	
	{
		setUsers(new HashMap<String, User>());
		setMessages(new LinkedList<ChatRoomMessage>());
	}
	
	public boolean isFull() {
		return users.size() >= maxNumberOfUsers;
	}
	
	public void addMessage(ChatRoomMessage message) {
		if(getMessages().size() >= MAXIMUM_NUMBER_OF_PERSISTED_MESSAGES) {
			getMessages().removeLast();
		}
		
		messages.addFirst(message);
	}
	
	public void addUser(User user) {
		users.put(user.getName(), user);
	}
	
	public boolean contains(String username) {
		return users.get(username) != null;
	}
	
	public boolean contains(User user) {
		return contains(user.getName());
	}
	
	public int getTotalNumberOfUsers() {
		return users.size();
	}
	
	public void removeUser(User user) {
		messages.add(new LeaveRoomMessage(user));
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

	public LinkedList<ChatRoomMessage> getMessages() {
		return messages;
	}

	public void setMessages(LinkedList<ChatRoomMessage> messages) {
		this.messages = messages;
	}

	public Map<String, User> getUsers() {
		return users;
	}

	public void setUsers(Map<String, User> users) {
		this.users = users;
	}
	
}
