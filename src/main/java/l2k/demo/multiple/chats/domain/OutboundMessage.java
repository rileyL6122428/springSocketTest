package l2k.demo.multiple.chats.domain;

public class OutboundMessage extends InboundMessage {
	
	private String timeStamp;
	
	public OutboundMessage(InboundMessage inboundMessage) {
		this.sender = inboundMessage.getSender();
		this.body = inboundMessage.getBody();
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
