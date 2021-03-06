package l2k.trivia.server.domain.chat;

import java.util.Date;

import l2k.trivia.server.domain.User;

public class ChatRoomMessage {
	
	protected String body;
	protected Sender sender;
	protected Date timestamp;
	
	public ChatRoomMessage() {}
	
	public ChatRoomMessage(User user, String body) {
		setSender(user);
		setBody(body);
		setTimestamp(new Date());
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
