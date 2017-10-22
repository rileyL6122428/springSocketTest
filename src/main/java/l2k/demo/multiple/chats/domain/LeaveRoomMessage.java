package l2k.demo.multiple.chats.domain;

import java.util.Date;

public class LeaveRoomMessage extends ChatRoomMessage {
	
	public LeaveRoomMessage(User user) {
		setSender(new Moderator());
		setBody(user.getName() + " has left the chat.");
		setTimestamp(new Date());
	}
	
}
