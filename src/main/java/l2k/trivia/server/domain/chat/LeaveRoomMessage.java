package l2k.trivia.server.domain.chat;

import java.util.Date;

import l2k.trivia.server.domain.User;

public class LeaveRoomMessage extends ChatRoomMessage {
	
	public LeaveRoomMessage(User user) {
		setSender(new Moderator());
		setBody(user.getName() + " has left the chat.");
		setTimestamp(new Date());
	}
	
}
