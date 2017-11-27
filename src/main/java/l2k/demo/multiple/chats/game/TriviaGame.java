package l2k.demo.multiple.chats.game;

import java.util.Map;

public class TriviaGame {

	private ScoreKeeper scoreKeeper;
	private QuestionRoll questionRoll;
	private Question currentQuestion;
	private AnswerMap currentQuestionAnswerMap;
	private PlayerScoreMap playerScoreMap;

	public TriviaGame(ScoreKeeper scoreKeeper, QuestionRoll questionRoll, PlayerScoreMap playerScoreMap) {
		this.scoreKeeper = scoreKeeper;
		this.questionRoll = questionRoll;
		this.playerScoreMap = playerScoreMap;
		setupNextQuestion();
	}

	public int getQuestionCount() {
		return questionRoll.getQuestionCount();
	}

	public int getCompletedQuestionCount() {
		return questionRoll.getCompletedQuestionCount();
	}

	public boolean isFinished() {
		return currentQuestion == null;
	}
	
	public void closeCurrentQuestion() {
		setupNextQuestion();
	}
	
	private void setupNextQuestion() {
		currentQuestion = questionRoll.getNextQuestion();
		currentQuestionAnswerMap = new AnswerMapBuilder().setQuestion(currentQuestion).build();
	}

	public String getCurrentQuestionText() {
		return currentQuestion.getText();
	}

	public AnswerMap getCurrentQuestionAnswerMap() {
		return currentQuestionAnswerMap;
	}

	public Map<Player, Integer> getPlayerScores() {
		return playerScoreMap.getPlayerScores();
	}
	
}
