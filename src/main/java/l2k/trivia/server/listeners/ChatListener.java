package l2k.trivia.server.listeners;

import l2k.trivia.server.domain.chat.Chat;

public interface ChatListener {
	
	public void fireChatUpdate(Chat chat);
	
}
