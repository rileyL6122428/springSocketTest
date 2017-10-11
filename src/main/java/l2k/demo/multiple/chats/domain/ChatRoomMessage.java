package l2k.demo.multiple.chats.domain;

import java.util.Date;

public class ChatRoomMessage {
	
	private String body;
	private Sender sender;
	private Date timeStamp;

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

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
