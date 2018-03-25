package l2k.trivia.server.domain.chat;

import java.util.Date;

import l2k.trivia.server.domain.User;

public class JoinRoomMessage extends ChatRoomMessage {
	
	public JoinRoomMessage(User user, String roomName) {
		setSender(new Moderator());
		setBody(user.getName() + " joins the " + roomName + " room!");
		setTimestamp(new Date());
	}
	
}
