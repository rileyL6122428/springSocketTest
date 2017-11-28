package l2k.demo.multiple.chats.game;

import java.util.List;
import java.util.Map;

public class TriviaGame {

	private ScoreKeeper scoreKeeper;
	private QuestionRoll questionRoll;
	
//	private Question currentQuestion;
//	private List<String> currentQuestionAnswers;
//	private PlayerAnswerReporter currentQuestionPlayerAnswerMap;
	
	private TriviaRound currentRound;
	
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
		return questionRoll.finished();
	}
	
	public void closeCurrentQuestion() {
		setupNextQuestion();
	}
	
	private void setupNextQuestion() {
		currentRound = new TriviaRound(questionRoll.getNextQuestion());
//		currentQuestion = questionRoll.getNextQuestion();
//		currentQuestionAnswers = new AnswerListBuilder().setQuestion(currentQuestion).build();
		
	}

	public String getCurrentQuestionText() {
		return currentRound.getQuestionText();
	}

	public List<String> getCurrentQuestionAnswers() {
		return currentRound.getAnswers();
	}

	public Map<Player, Integer> getPlayerScores() {
		return playerScoreMap.getPlayerScores();
	}

	public void submitAnswer(Player player, String answer) {
		currentRound.submitAnswer(player, answer);
	}
	
}
