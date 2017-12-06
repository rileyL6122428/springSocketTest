package l2k.demo.multiple.chats.game;

import java.util.List;
import java.util.Map;

public class TriviaGame {

	private ScoreKeeper scoreKeeper;
	private RollCall<TriviaRound> triviaRoundRollCall;
	
	private TriviaRound currentRound;

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
		registerScores();
		setupNextRound();
	}
	
	private void registerScores() {
		currentRound.getPlayersWithCorrectAnswer().forEach(scoreKeeper::incrementScore);
	}
	
	private void setupNextRound() {
		currentRound = triviaRoundRollCall.getNextItem();
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
