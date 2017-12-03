package l2k.demo.multiple.chats.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnswerListBuilder {
	
	private Answer correctAnswer;
	private List<Answer> fakeAnswers;

	public List<Answer> build() {
		return shuffledAnswers();
	}
	
	private List<Answer> shuffledAnswers() {
		List<Answer> answers = new ArrayList<Answer>();
		answers.add(correctAnswer);
		answers.addAll(fakeAnswers);
		Collections.shuffle(answers);
		return answers;
	}
	
	public AnswerListBuilder setCorrectAnswer(Answer correctAnswer) {
		this.correctAnswer = correctAnswer;
		return this;
	}
	
	public AnswerListBuilder setFakeAnswers(List<Answer> fakeAnswers) {
		this.fakeAnswers = fakeAnswers;
		return this;
	}

}
