package l2k.demo.multiple.chats.game;

import java.util.List;

public class QuestionManager {
	
	private int questionIdx = 0;
	private List<Question> questions;

	public QuestionManager(List<Question> questions) {
		this.questions = questions;
	}

	public int getCompletedQuestionCount() {
		return questionIdx;
	}

	public int getQuestionCount() {
		return questions.size();
	}

	public boolean questionsCompleted() {
		return questionIdx >= questions.size();
	}

	public void nextQuestion() {
		questionIdx++;
	}

	public String getCurrentQuestion() {
		return questions.get(questionIdx).getText();
	}

}
