package l2k.demo.multiple.chats.game;

import java.util.ArrayList;
import java.util.List;

public class QuestionBuilder {

	private String text;
	private String answer;
	private List<String> fakeAnswers = new ArrayList<String>();

	public Question build() {
		return new Question(
			text,
			answer,
			fakeAnswers
		);
	}
	
	public QuestionBuilder setText(String text) {
		this.text = text;
		return this;
	}

	public QuestionBuilder setAnswer(String answer) {
		this.answer = answer;
		return this;
	}

	public QuestionBuilder addFakeAnswer(String fakeAnswer) {
		this.fakeAnswers.add(fakeAnswer);
		return this;
	}

}
