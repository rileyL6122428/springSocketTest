package l2k.trivia.server.domain.chat;

import java.util.LinkedList;

public class Chat {
	
	private static final int MESSAGE_CAPACITY = 75;
	
	private LinkedList<ChatRoomMessage> messages;
	
	{
		messages = new LinkedList<ChatRoomMessage>();
	}

	public LinkedList<ChatRoomMessage> getMessages() {
		return messages;
	}

	public void addMessage(ChatRoomMessage message) {
		if(getMessages().size() >= MESSAGE_CAPACITY) {
			getMessages().removeLast();
		}
		
		messages.addFirst(message);
	}
	
}
