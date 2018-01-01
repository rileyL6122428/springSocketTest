package l2k.trivia.server.services;

import l2k.trivia.game.Answer;
import l2k.trivia.server.domain.ChatRoomMessage;
import l2k.trivia.server.domain.JoinRoomMessage;
import l2k.trivia.server.domain.Room;
import l2k.trivia.server.domain.User;

public class RoomManager {
	
	private Room room;
	private GameManager gameManager;
	private ChatManager chatManager;	
	
	public RoomManager(Room room, RoomMessagingTemplate roomMessagingTemplate) {
		this.room = room;
		this.chatManager = new ChatManager();
		this.gameManager = new GameManager(room.getName(), roomMessagingTemplate);
	}
	
	public boolean isFull() {
		return room.isFull();
	}
	
	public void addUser(User user) {
		room.addUser(user);
		gameManager.addPlayer(user);
		room.addMessage(new JoinRoomMessage(user));
	}
	
	public void removeUser(User user) {
		room.removeUser(user);
	}
	
	public void addMessage(ChatRoomMessage message) {
		room.addMessage(message);
	}
	
	public boolean contains(User user) {
		return user != null && room.contains(user.getName());
	}
	
	public void submitGameAnswer(User user, Answer answer) {
		if(room.contains(user.getName()))
			gameManager.submitAnswer(user, answer);
	}
	
	public Room getRoom() {
		return room;
	}

}
