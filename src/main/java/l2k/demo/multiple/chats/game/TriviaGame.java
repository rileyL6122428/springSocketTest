package l2k.demo.multiple.chats.game;

import java.util.List;
import java.util.Map;

public class TriviaGame {

	private ScoreKeeper scoreKeeper;
	private Roll<TriviaRound> roundRoll;
	
	private TriviaRound currentRound;

	public TriviaGame(ScoreKeeper scoreKeeper, Roll<TriviaRound> roundRoll) {
		this.scoreKeeper = scoreKeeper;
		this.roundRoll = roundRoll;
		setupNextRound();
	}

	public int getQuestionCount() {
		return roundRoll.getTotalItemCount();
	}

	public int getCompletedQuestionCount() {
		return roundRoll.getCompletedItemCount();
	}

	public boolean isFinished() {
		return roundRoll.isFinished();
	}
	
	public void closeCurrentRound() {
		registerScores();
		setupNextRound();
	}
	
	private void registerScores() {
		currentRound.getPlayersWithCorrectAnswer().forEach(scoreKeeper::incrementScore);
	}
	
	private void setupNextRound() {
		currentRound = roundRoll.getNextItem();
	}

	public Question getCurrentQuestion() {
		return currentRound.getQuestion();
	}

	public List<Answer> getCurrentQuestionAnswers() {
		return currentRound.getAnswers();
	}

	public Map<Player, Integer> getPlayerScores() {
		return scoreKeeper.getScoreMap();
	}

	public void submitAnswer(Player player, Answer answer) {
		currentRound.submitAnswer(player, answer);
	}
	
}
