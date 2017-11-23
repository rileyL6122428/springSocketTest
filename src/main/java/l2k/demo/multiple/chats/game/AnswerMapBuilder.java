package l2k.demo.multiple.chats.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AnswerMapBuilder {
	
	private Question question;

	public AnswerMap build() {
		if(question == null) return null;
		
		List<String> shuffledAnswers = shuffledAnswers(question);
		return new AnswerMap(shuffledAnswers);
	}
	
	private List<String> shuffledAnswers(Question question) {
		List<String> answers = new ArrayList<String>();
		answers.add(question.getAnswer());
		answers.addAll(question.getFakeAnswers());
		Collections.shuffle(answers);
		return answers;
	}
	
	public AnswerMapBuilder setQuestion(Question question) {
		this.question = question;
		return this;
	}

}
