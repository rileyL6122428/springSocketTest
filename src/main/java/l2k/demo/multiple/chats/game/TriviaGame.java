package l2k.demo.multiple.chats.game;

import java.util.List;
import java.util.Map;

public class TriviaGame {

	private ScoreKeeper scoreKeeper;
	private QuestionRoll questionRoll;
	private TriviaRoundFactory triviaRoundFactory;
	
	private TriviaRound currentRound;

	public TriviaGame(ScoreKeeper scoreKeeper, QuestionRoll questionRoll, TriviaRoundFactory triviaRoundFactory) {
		this.scoreKeeper = scoreKeeper;
		this.questionRoll = questionRoll;
		this.triviaRoundFactory = triviaRoundFactory;
		setupNextRound();
	}

	public int getQuestionCount() {
		return questionRoll.getQuestionCount();
	}

	public int getCompletedQuestionCount() {
		return questionRoll.getCompletedQuestionCount();
	}

	public boolean isFinished() {
		return questionRoll.isFinished();
	}
	
	public void closeCurrentRound() {
		registerScores();
		setupNextRound();
	}
	
	private void registerScores() {
		currentRound.getPlayersWithCorrectAnswer().forEach(scoreKeeper::incrementScore);
	}
	
	private void setupNextRound() {
		currentRound = triviaRoundFactory.buildTriviaRound(questionRoll.getNextQuestion());
	}

	public String getCurrentQuestionText() {
		return currentRound.getQuestionText();
	}

	public List<String> getCurrentQuestionAnswers() {
		return currentRound.getAnswers();
	}

	public Map<Player, Integer> getPlayerScores() {
		return scoreKeeper.getScoreMap();
	}

	public void submitAnswer(Player player, String answer) {
		currentRound.submitAnswer(player, answer);
	}
	
}
