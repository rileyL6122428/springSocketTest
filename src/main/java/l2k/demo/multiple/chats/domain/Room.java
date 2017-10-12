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
		setUsers(new HashMap<String, Principal>());
		setMessages(new LinkedList<ChatRoomMessage>());
	}
	
	public boolean isFull() {
		return getUsers().size() >= maxNumberOfUsers;
	}
	
	public void addMessage(ChatRoomMessage message) {
		if(getMessages().size() >= MAXIMUM_NUMBER_OF_PERSISTED_MESSAGES) {
			getMessages().removeFirst();
		}
		
		getMessages().addLast(message);
	}
	
	public void addUser(Principal user) {
		getUsers().put(user.getName(), user);
	}
	
	public boolean contains(User user) {
		return getUsers().get(user.getName()) != null;
	}
	
	public int getTotalNumberOfUsers() {
		return getUsers().size();
	}
	
	public void removeUser(Principal user) {
		getUsers().remove(user.getName());
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

	public Map<String, Principal> getUsers() {
		return users;
	}

	public void setUsers(Map<String, Principal> users) {
		this.users = users;
	}
	
}
