package l2k.demo.multiple.chats.messages;

import l2k.demo.multiple.chats.domain.User;

public class InboundMessage {
	
	User sender;
	String body;
	
	public User getSender() {
		return sender;
	}
	
	public void setSender(User sender) {
		this.sender = sender;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}
