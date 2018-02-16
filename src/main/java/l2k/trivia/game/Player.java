package l2k.trivia.game;

import l2k.trivia.server.domain.User;

public class Player {

	private int score;
	private String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	public void incrementScore() {
		this.score++;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getName() {
		return name;
	}

}
