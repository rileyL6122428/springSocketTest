package l2k.trivia.server.domain;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import l2k.trivia.server.domain.chat.Chat;
import l2k.trivia.server.domain.chat.ChatRoomMessage;
import l2k.trivia.server.domain.chat.LeaveRoomMessage;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Room {
	
	private String name;
	private Map<String, User> users;
	private int userCapacity;
	private final Chat chat;
	
	{
		setUsers(new HashMap<String, User>());
		chat = new Chat();
	}
	
	public boolean isFull() {
		return users.size() >= userCapacity;
	}
	
	public void addMessage(ChatRoomMessage message) {
		chat.addMessage(message);
	}
	
	public void addUser(User user) {
		users.put(user.getName(), user);
	}
	
	public boolean contains(String username) {
		return users.get(username) != null;
	}
	
	public boolean contains(User user) {
		return user != null && contains(user.getName());
	}
	
	public int getTotalNumberOfUsers() {
		return users.size();
	}
	
	public void removeUser(User user) {
		addMessage(new LeaveRoomMessage(user));
		users.remove(user.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserCapacity() {
		return userCapacity;
	}

	public void setUserCapacity(int userCapacity) {
		this.userCapacity = userCapacity;
	}

	public Chat getChat() {
		return chat;
	}

	public Map<String, User> getUsers() {
		return users;
	}

	public void setUsers(Map<String, User> users) {
		this.users = users;
	}
	
}
