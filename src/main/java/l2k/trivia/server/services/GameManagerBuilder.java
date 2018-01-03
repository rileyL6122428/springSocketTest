package l2k.trivia.server.services;

import java.util.HashMap;
import java.util.Map;

import l2k.trivia.game.Player;

public class GameManagerBuilder {
	
	private String roomName;
	private int minPlayers;
	private RoomMessagingTemplate messagingTemplate;
	private Map<String, Player> namesToPlayers = new HashMap<String, Player>();
	
	public GameManager build() {
		return new GameManager(
			roomName,
			messagingTemplate,
			minPlayers,
			namesToPlayers
		);
	}
	
	public GameManagerBuilder setRoomName(String roomName) {
		this.roomName = roomName;
		return this;
	}
	
	public GameManagerBuilder setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
		return this;
	}
	
	public GameManagerBuilder setMessagingTemplate(RoomMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
		return this;
	}
	
}
