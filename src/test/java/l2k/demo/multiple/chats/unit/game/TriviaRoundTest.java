package l2k.demo.multiple.chats.unit.game;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import l2k.demo.multiple.chats.game.Answer;
import l2k.demo.multiple.chats.game.Question;
import l2k.demo.multiple.chats.game.TriviaRound;
import l2k.demo.multiple.chats.game.TriviaRoundBuilder;

class TriviaRoundTest {
	
	private TriviaRound triviaRound;
	
	private Question question;
	private Answer correctAnswer;
	private List<Answer> fakeAnswers;
	
	@BeforeEach
	public void setupQuestionAndAnswers() {
		question = new Question("What is the smallest prime number?");
		correctAnswer = new Answer("2");
		fakeAnswers = asList(
			new Answer("3"),
			new Answer("4"),
			new Answer("5")
		);
	}
	
	@BeforeEach
	public void setupTriviaRound() {
		triviaRound = new TriviaRoundBuilder()
			.setQuestion(question)
			.setCorrectAnswer(correctAnswer)
			.addFakeAnswers(fakeAnswers)
			.build();
	}

	@Test
	public void getAnswersReturnsAListContainingTheCorrectAndFakeAnswers() {
		final List<Answer> answers = triviaRound.getAnswers();
		
		assertTrue(answers.contains(correctAnswer));
		
		fakeAnswers.forEach((fakeAnswer) -> {
			assertTrue(answers.contains(fakeAnswer));
		});
	}

}
