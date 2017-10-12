package l2k.demo.multiple.chats.domain;

import java.security.Principal;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "sessionId" })
public class User implements Principal, Sender {
	
	private String name;
	private UUID sessionId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean equals(User otherUser) {
		return sessionId.equals(otherUser.getSessionId());
	}

	public UUID getSessionId() {
		return sessionId;
	}

	public void setSessionId(UUID sessionId) {
		this.sessionId = sessionId;
	}
	
}
