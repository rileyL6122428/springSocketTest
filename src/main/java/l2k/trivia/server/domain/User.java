package l2k.trivia.server.domain;

import java.security.Principal;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import l2k.trivia.game.Player;
import l2k.trivia.server.domain.chat.Sender;

@JsonIgnoreProperties(value = { "sessionId" })
public class User implements Principal, Sender, Player {
	
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
	
	@Override
	public boolean equals(Object otherObject) {
		return otherObject instanceof User && equals((User) otherObject);
	}
	

	public UUID getSessionId() {
		return sessionId;
	}

	public void setSessionId(UUID sessionId) {
		this.sessionId = sessionId;
	}
	
}
