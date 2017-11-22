package l2k.demo.multiple.chats.game;

public class TriviaGame {

	private ScoreKeeper scoreKeeper;
	private QuestionManager questionManager;

	public TriviaGame(ScoreKeeper scoreKeeper, QuestionManager questionManager) {
		this.scoreKeeper = scoreKeeper;
		this.questionManager = questionManager;
	}

	public int getQuestionCount() {
		return questionManager.getQuestionCount();
	}

	public int getCompletedQuestionCount() {
		return questionManager.getCompletedQuestionCount();
	}

	public void nextQuestion() {
		questionManager.nextQuestion();
	}

	public boolean isFinished() {
		return questionManager.questionsCompleted();
	}

	
	
}
