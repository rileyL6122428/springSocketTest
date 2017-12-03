package l2k.demo.multiple.chats.game;

public class TriviaRoundFactory {
	
	public TriviaRound buildTriviaRound(Question question) {
		return new TriviaRound(question);
	}
	
}
