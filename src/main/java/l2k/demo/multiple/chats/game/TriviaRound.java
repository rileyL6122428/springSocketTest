package l2k.demo.multiple.chats.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TriviaRound {

	private Question question;
	private List<String> answers;
	private Map<Player, String> playersToAnswers;
	
	public TriviaRound(Question question) {
		this.question = question;
		this.answers = new AnswerListBuilder().setQuestion(question).build();
		this.playersToAnswers = new HashMap<Player, String>();
	}
	
	public String getQuestionText() {
		return question.getText();
	}
	
	public List<String> getAnswers() {
		return answers;
	}
	
	public void submitAnswer(Player player, String answerText) {
		playersToAnswers.put(player, answerText);
	}
	
	public List<Player> getPlayersWithCorrectAnswer() {
		List<Player> players = new ArrayList<Player>();
		
		for(Map.Entry<Player, String> playerAnswerEntry : playersToAnswers.entrySet()) {
			if(playerAnswerEntry.getValue() == question.getAnswer())
				players.add(playerAnswerEntry.getKey());
		}
		
		return players;
	}
}
