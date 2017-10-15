package l2k.demo.multiple.chats.domain;

import java.util.Date;

public class JoinRoomMessage extends ChatRoomMessage {
	
	public JoinRoomMessage(User user) {
		setSender(new Moderator());
		setBody(user.getName() + " joins the CHAT!");
		setTimestamp(new Date());
	}
	
}
