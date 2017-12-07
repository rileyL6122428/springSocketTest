package l2k.trivia.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TriviaRound {

	private Question question;
	private Answer correctAnswer;
	private List<Answer> answers;
	private Map<Player, Answer> playersToAnswers;
	
	public TriviaRound(Question question, Answer correctAnswer, List<Answer> answers, Map<Player, Answer> playersToAnswers) {
		this.question = question;
		this.correctAnswer = correctAnswer;
		this.answers = answers;
		this.playersToAnswers = playersToAnswers;
	}
	
	public Question getQuestion() {
		return question;
	}
	
	public List<Answer> getAnswers() {
		return answers;
	}
	
	public void submitAnswer(Player player, Answer answer) {
		playersToAnswers.put(player, answer);
	}
	
	public List<Player> getPlayersWithCorrectAnswer() {
		List<Player> players = new ArrayList<Player>();
		
		for(Map.Entry<Player, Answer> playerAnswerEntry : playersToAnswers.entrySet()) {
			if(playerAnswerEntry.getValue().equals(correctAnswer))
				players.add(playerAnswerEntry.getKey());
		}
		
		return players;
	}
}
