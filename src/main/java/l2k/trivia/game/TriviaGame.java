package l2k.trivia.game;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import l2k.trivia.server.listeners.GameListener;

public class TriviaGame {
	
	@Autowired private List<GameListener> listeners;

	private ScoreKeeper scoreKeeper;
	private RollCall<TriviaRound> triviaRoundRollCall;
	
	private TriviaRound currentRound;
	private boolean acceptingAnswers;

	public TriviaGame(ScoreKeeper scoreKeeper, RollCall<TriviaRound> triviaRoundRollCall) {
		this.scoreKeeper = scoreKeeper;
		this.triviaRoundRollCall = triviaRoundRollCall;
		setupNextRound();
	}

	public int getRoundCount() {
		return triviaRoundRollCall.getTotalItemCount();
	}

	public int getCompletedRoundCount() {
		return triviaRoundRollCall.getRetrievedItemCount() - 1;
	}

	public boolean isFinished() {
		return triviaRoundRollCall.isFinished();
	}
	
	public void closeCurrentRound() {
		currentRound.getPlayersWithCorrectAnswer().forEach(scoreKeeper::incrementScore);
		acceptingAnswers = false;
	}
	
	public void setupNextRound() {
		currentRound = triviaRoundRollCall.getNextItem();
		acceptingAnswers = true;
	}

	public void submitAnswer(Player player, Answer answer) {
		if(acceptingAnswers)
			currentRound.submitAnswer(player, answer);
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
	
	public void removePlayer(Player player) {
		scoreKeeper.removeUser(player);
	}
	
	private void notifyListeners() {
		listeners.forEach((listener) -> listener.fireGameUpdate(this));
	}
	
}
