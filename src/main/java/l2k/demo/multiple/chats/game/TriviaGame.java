package l2k.demo.multiple.chats.game;

public class TriviaGame {

	private ScoreKeeper scoreKeeper;
	private QuestionRoll questionRoll;
	private Question currentQuestion;
	private AnswerMap currentQuestionAnswerMap;

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
	
}
