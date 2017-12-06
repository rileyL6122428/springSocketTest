package l2k.demo.multiple.chats.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TriviaRoundBuilder {
	
	private Question question;
	private Answer correctAnswer;
	private List<Answer> fakeAnswers = new ArrayList<Answer>();

	public TriviaRound build() {
		return new TriviaRound(
			question,
			correctAnswer,
			new AnswerListBuilder().setCorrectAnswer(correctAnswer).setFakeAnswers(fakeAnswers).build(),
			new HashMap<Player, Answer>()
		);
	}
	
	public TriviaRoundBuilder setQuestion(Question question) {
		this.question = question;
		return this;
	}

	public TriviaRoundBuilder setCorrectAnswer(Answer answer) {
		this.correctAnswer = answer;
		return this;
	}

	public TriviaRoundBuilder addFakeAnswer(Answer fakeAnswer) {
		this.fakeAnswers.add(fakeAnswer);
		return this;
	}

	public TriviaRoundBuilder addFakeAnswers(List<Answer> fakeAnswers) {
		fakeAnswers.forEach(this::addFakeAnswer);
		return this;
	}
	
}
