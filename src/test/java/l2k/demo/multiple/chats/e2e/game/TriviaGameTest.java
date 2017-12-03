package l2k.demo.multiple.chats.e2e.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import l2k.demo.multiple.chats.game.AnswerMap;
import l2k.demo.multiple.chats.game.Player;
import l2k.demo.multiple.chats.game.Question;
import l2k.demo.multiple.chats.game.QuestionBuilder;
import l2k.demo.multiple.chats.game.TriviaGame;
import l2k.demo.multiple.chats.game.TriviaGameBuilder;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TriviaGameTest {
	
	private TriviaGame game;
	private List<Question> questions;
	
	private Player tom;
	private Player betty;
	private List<Player> players;
	
	@BeforeEach
	public void setupPlayers() {
		tom = new Player("Tom");
		betty = new Player("Betty");
		players = Arrays.asList(tom, betty);
	}
	
	@BeforeEach
	public void setupQuestions() {		
		questions = new ArrayList<Question>() {{
			add(new QuestionBuilder()
					.setText("What is a trumpet?")
					.setAnswer("A brass instrument")
					.addFakeAnswer("A percussion instrument")
					.addFakeAnswer("A woodwind instrument")
					.addFakeAnswer("A snack")
					.build()
			);
			
			add(new QuestionBuilder()
					.setText("What is a snare drum?")
					.setAnswer("A percussion instrument")
					.addFakeAnswer("A brass instrument")
					.addFakeAnswer("A woodwind instrument")
					.addFakeAnswer("A kitchen utensil")
					.build()
			);
		}};
	}
	
	@BeforeEach
	public void setupGame() {
		game = new TriviaGameBuilder()
				.setPlayers(players)
				.setQuestions(questions)
				.build();		
	}

	@Test
	public void reportsTheNumberOfQuestionsCompleted() {
		assertEquals(2, game.getQuestionCount());
		assertEquals(0, game.getCompletedQuestionCount());
		
		game.closeCurrentRound();
		assertEquals(2, game.getQuestionCount());
		assertEquals(1, game.getCompletedQuestionCount());
		
		game.closeCurrentRound();
		assertEquals(2, game.getQuestionCount());
		assertEquals(2, game.getCompletedQuestionCount());
		assertTrue(game.isFinished());
	}
	
	@Test
	public void asksQuestionsInTheOrderProvided() {
		assertEquals("What is a trumpet?", game.getCurrentQuestionText());
		
		game.closeCurrentRound();
		assertEquals("What is a snare drum?", game.getCurrentQuestionText());
	}
	
	@Test
	public void providesAListOfAnswersForEachQuestion() {
		List<String> questionOneAnswers = game.getCurrentQuestionAnswers();
		
		AnswerCounts answerCounts = new AnswerCounts(
			"A brass instrument", "A percussion instrument", "A woodwind instrument", "A snack"
		);
		
		questionOneAnswers.forEach(answerCounts::increment);
		
		assertEquals(1, answerCounts.getCount("A brass instrument"));
		assertEquals(1, answerCounts.getCount("A percussion instrument"));
		assertEquals(1, answerCounts.getCount("A woodwind instrument"));
		assertEquals(1, answerCounts.getCount("A snack"));
	}

	class AnswerCounts {
		
		private Map<String, Integer> counts = new HashMap<String, Integer>();
		
		AnswerCounts(String... answers) {
			for(String answer : answers) {
				counts.put(answer, 0);
			}
		}
		
		void increment(String answer) {
			if(counts.get(answer) == null) throw new RuntimeException();
			counts.put(answer, counts.get(answer) + 1);
		}
		
		int getCount(String answer) {
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
		assertEquals("What is a trumpet?", game.getCurrentQuestionText());
		game.submitAnswer(tom, "A snack");
		game.submitAnswer(betty, "A brass instrument");
		game.closeCurrentRound();
		
		verifyScore(tom, 0);
		verifyScore(betty, 1);
		
		assertEquals("What is a snare drum?", game.getCurrentQuestionText());
		game.submitAnswer(tom, "A kitchen utensil");
		game.submitAnswer(betty, "A percussion instrument");
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
