package l2k.demo.multiple.chats.game;

import java.util.Collections;
import java.util.List;

public class Question {

	private String text;
	private String answer;
	private List<String> fakeAnswers;

	public Question(String text, String answer, List<String> fakeAnswers) {
		this.text = text;
		this.answer = answer;
		this.fakeAnswers = fakeAnswers;
	}

	public String getText() {
		return text;
	}

	public String getAnswer() {
		return answer;
	}
	
	public List<String> getFakeAnswers() {
		return Collections.unmodifiableList(fakeAnswers);
	}

}
