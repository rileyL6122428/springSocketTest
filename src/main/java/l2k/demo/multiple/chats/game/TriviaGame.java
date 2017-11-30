package l2k.demo.multiple.chats.game;

import java.util.List;
import java.util.Map;

public class TriviaGame {

	private ScoreKeeper scoreKeeper;
	private QuestionRoll questionRoll;
	
	private TriviaRound currentRound;

	public TriviaGame(ScoreKeeper scoreKeeper, QuestionRoll questionRoll) {
		this.scoreKeeper = scoreKeeper;
		this.questionRoll = questionRoll;
		setupNextQuestion();
	}

	public int getQuestionCount() {
		return questionRoll.getQuestionCount();
	}

	public int getCompletedQuestionCount() {
		return questionRoll.getCompletedQuestionCount();
	}

	public boolean isFinished() {
		return questionRoll.finished();
	}
	
	public void closeCurrentQuestion() {
		registerScores();
		setupNextQuestion();
	}
	
	private void setupNextQuestion() {
		currentRound = new TriviaRound(questionRoll.getNextQuestion());
	}
	
	private void registerScores() {
		currentRound.getPlayersWithCorrectAnswer().forEach(scoreKeeper::incrementScore);
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
