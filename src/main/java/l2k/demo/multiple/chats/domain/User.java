package l2k.demo.multiple.chats.domain;

public class User {
	
	private String name;
	private String sessionId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public boolean equals(User otherUser) {
		return this.sessionId == otherUser.getSessionId();
	}
	
	public int hashcode() {
		return sessionId.hashCode() * 3;
		
	}
	
}
