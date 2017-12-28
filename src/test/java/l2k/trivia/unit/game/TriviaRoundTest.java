package l2k.trivia.unit.game;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import l2k.trivia.game.Answer;
import l2k.trivia.game.Player;
import l2k.trivia.game.Question;
import l2k.trivia.game.TriviaRound;
import l2k.trivia.game.TriviaRoundBuilder;
import l2k.trivia.server.domain.User;
import l2k.trivia.utils.UserFactory;

class TriviaRoundTest {
	
	private TriviaRound triviaRound;
	
	private Question question;
	private Answer correctAnswer;
	private List<Answer> fakeAnswers;
	
	private UserFactory userFactory = new UserFactory();
	
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
		assertEquals(4, answers.size());
		
		assertTrue(answers.contains(correctAnswer));
		fakeAnswers.forEach((fakeAnswer) -> {
			assertTrue(answers.contains(fakeAnswer));
		});
	}
	
	@Test
	public void returnsUsersThatSubmittedCorrectAnswers() {
		Player sally = userFactory.newUser("Sally");
		Player tommy = userFactory.newUser("Tommy");
		Player bobby = userFactory.newUser("Bobby");
		Player betty = userFactory.newUser("Betty");
		
		triviaRound.submitAnswer(sally, correctAnswer);
		triviaRound.submitAnswer(tommy, fakeAnswers.get(0));
		triviaRound.submitAnswer(bobby, fakeAnswers.get(2));
		triviaRound.submitAnswer(betty, correctAnswer);
		
		final List<Player> players = triviaRound.getPlayersWithCorrectAnswer();
		assertEquals(2, players.size());
		
		players.forEach((player) -> {
			assertTrue(players.contains(player));
		});
	}
	
}
