package l2k.trivia.server.domain.chat;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import l2k.trivia.server.domain.Room;
import l2k.trivia.server.listeners.ChatListener;


public class Chat {
	
	@Autowired private List<ChatListener> chatListeners;
	private static final int MESSAGE_CAPACITY = 75;	
	private LinkedList<ChatRoomMessage> messages;
	private Room room;
	
	public Chat() {}
	
	public Chat(Room room) {
		this.room = room;
	}
	
	{
		this.messages = new LinkedList<ChatRoomMessage>();		
	}

	public LinkedList<ChatRoomMessage> getMessages() {
		return messages;
	}

	public void addMessage(ChatRoomMessage message) {
		if(getMessages().size() >= MESSAGE_CAPACITY)
			getMessages().removeLast();
		
		messages.addFirst(message);
		notifyListeners();
	}
	
	public String getRoomName() {
		return room.getName();
	}
	
	private void notifyListeners() {
		chatListeners.forEach((listener) -> listener.fireChatUpdate(this));
	}

	public void setRoom(Room room) {
		this.room = room;
	}
}
