package l2k.trivia.e2e.game;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import l2k.trivia.game.Answer;
import l2k.trivia.game.Player;
import l2k.trivia.game.Question;
import l2k.trivia.game.TriviaGame;
import l2k.trivia.game.TriviaGameBuilder;
import l2k.trivia.game.TriviaRound;
import l2k.trivia.game.TriviaRoundBuilder;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TriviaGameTest {
	
	private TriviaGame game;
	private List<TriviaRound> triviaRounds;
	
	private Player tom;
	private Player betty;
	private List<Player> players;
	
	@BeforeEach
	public void setupPlayers() {
		tom = new Player("Tom");
		betty = new Player("Betty");
		players = asList(tom, betty);
	}
	
	@BeforeEach
	public void setupQuestions() {		
		triviaRounds = new ArrayList<TriviaRound>() {{
			add(new TriviaRoundBuilder()
					.setQuestion(new Question("What is a trumpet?"))
					.setCorrectAnswer(new Answer("A brass instrument"))
					.addFakeAnswer(new Answer("A percussion instrument"))
					.addFakeAnswer(new Answer("A woodwind instrument"))
					.addFakeAnswer(new Answer("A snack"))
					.build()
			);
			
			add(new TriviaRoundBuilder()
					.setQuestion(new Question("What is a snare drum?"))
					.setCorrectAnswer(new Answer("A percussion instrument"))
					.addFakeAnswer(new Answer("A brass instrument"))
					.addFakeAnswer(new Answer("A woodwind instrument"))
					.addFakeAnswer(new Answer("A kitchen utensil"))
					.build()
			);
		}};
	}
	
	@BeforeEach
	public void setupGame() {
		game = new TriviaGameBuilder()
				.setPlayers(players)
				.setRounds(triviaRounds)
				.build();		
	}

	@Test
	public void reportsTheNumberOfQuestionsCompleted() {
		assertEquals(2, game.getRoundCount());
		assertEquals(0, game.getCompletedRoundCount());
		
		game.closeCurrentRound();
		assertEquals(2, game.getRoundCount());
		assertEquals(1, game.getCompletedRoundCount());
		
		game.closeCurrentRound();
		assertEquals(2, game.getRoundCount());
		assertEquals(2, game.getCompletedRoundCount());
		
		assertTrue(game.isFinished());
	}
	
	@Test
	public void asksQuestionsInTheOrderProvided() {
		assertEquals(new Question("What is a trumpet?"), game.getCurrentQuestion());
		
		game.closeCurrentRound();
		assertEquals(new Question("What is a snare drum?"), game.getCurrentQuestion());
	}
	
	@Test
	public void providesAListOfAnswersForEachQuestion() {
		List<Answer> questionOneAnswers = game.getCurrentQuestionAnswers();
		
		AnswerCounts answerCounts = new AnswerCounts(
			new Answer("A brass instrument"), 
			new Answer("A percussion instrument"), 
			new Answer("A woodwind instrument"), 
			new Answer("A snack")
		);
		
		questionOneAnswers.forEach(answerCounts::increment);
		
		assertEquals(1, answerCounts.getCount(new Answer("A brass instrument")));
		assertEquals(1, answerCounts.getCount(new Answer("A percussion instrument")));
		assertEquals(1, answerCounts.getCount(new Answer("A woodwind instrument")));
		assertEquals(1, answerCounts.getCount(new Answer("A snack")));
	}

	class AnswerCounts {
		
		private Map<Answer, Integer> counts = new HashMap<Answer, Integer>();
		
		AnswerCounts(Answer... answers) {
			for(Answer answer : answers) {
				counts.put(answer, 0);
			}
		}
		
		void increment(Answer answer) {
			if(counts.get(answer) == null) throw new RuntimeException();
			counts.put(answer, counts.get(answer) + 1);
		}
		
		int getCount(Answer answer) {
			if(counts.get(answer) == null) throw new RuntimeException();
			return counts.get(answer);
		}
	}
	
	@Test
	public void providesMapsOfPlayersToTheirScores() {
		Map<Player, Integer> playerScoreMap = game.getPlayerScores();
		assertEquals(2, playerScoreMap.size());
		assertEquals(0, (int)playerScoreMap.get(tom));
		assertEquals(0, (int)playerScoreMap.get(betty));
	}
	
	@Test
	public void playersSubmitAnswersAndReceiveAPointForCorrectAnswers() {
		assertEquals(new Question("What is a trumpet?"), game.getCurrentQuestion());
		game.submitAnswer(tom, new Answer("A snack"));
		game.submitAnswer(betty, new Answer("A brass instrument"));
		game.closeCurrentRound();
		
		verifyScore(tom, 0);
		verifyScore(betty, 1);
		
		assertEquals(new Question("What is a snare drum?"), game.getCurrentQuestion());
		game.submitAnswer(tom, new Answer("A kitchen utensil"));
		game.submitAnswer(betty, new Answer("A percussion instrument"));
		game.closeCurrentRound();
		
		verifyScore(tom, 0);
		verifyScore(betty, 2);
		
		assertTrue(game.isFinished());
	}
	
	private void verifyScore(Player player, int expectedScore) {
		Map<Player, Integer> playerScoreMap = game.getPlayerScores();
		assertEquals(expectedScore, (int)playerScoreMap.get(player));
	}
}
