package l2k.trivia.server.listeners;

import l2k.trivia.game.TriviaGame;

public interface GameListener {
	
	public void fireGameUpdate(TriviaGame game);
	
}
