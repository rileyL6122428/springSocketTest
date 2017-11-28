package l2k.demo.multiple.chats.game;

import java.util.List;

public class QuestionRoll {
	
	private int questionIdx = 0;
	private List<Question> questions;

	public QuestionRoll(List<Question> questions) {
		this.questions = questions;
	}

	public int getCompletedQuestionCount() {
		return questionIdx - 1;
	}

	public int getQuestionCount() {
		return questions.size();
	}

	public Question getNextQuestion() {
		int nextQuestionIdx = questionIdx++;
		
		if(nextQuestionIdx >= questions.size()) {
			return null;
		} else {			
			Question question = questions.get(nextQuestionIdx);
			return question;			
		}
	}

	public boolean finished() {
		return questionIdx >= questions.size();
	}
}
