package l2k.demo.multiple.chats.domain;

import java.util.Date;

public class ChatRoomMessage {
	
	protected String body;
	protected Sender sender;
	protected Date timestamp;

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
