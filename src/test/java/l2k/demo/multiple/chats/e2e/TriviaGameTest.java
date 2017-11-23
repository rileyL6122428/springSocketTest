package l2k.demo.multiple.chats.e2e;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
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
	private List<Player> players;
	private List<Question> questions;
	
	@BeforeEach
	public void setupPlayers() {		
		players = new ArrayList<Player>() {{
			add(new Player("Tom"));
			add(new Player("Betty"));
		}};
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
		
		game.closeCurrentQuestion();
		assertEquals(2, game.getQuestionCount());
		assertEquals(1, game.getCompletedQuestionCount());
		
		game.closeCurrentQuestion();
		assertEquals(2, game.getQuestionCount());
		assertEquals(2, game.getCompletedQuestionCount());
		assertTrue(game.isFinished());
	}
	
	@Test
	public void asksQuestionsInTheOrderProvided() {
		assertEquals("What is a trumpet?", game.getCurrentQuestionText());
		
		game.closeCurrentQuestion();
		assertEquals("What is a snare drum?", game.getCurrentQuestionText());
	}
	
	@Test
	public void providesAKeyMapOfAnswersForEachQuestion() {
		AnswerMap questionOneAnswerMap = game.getCurrentQuestionAnswerMap();
		
		AnswerCounts answerCounts = new AnswerCounts(
			"A brass instrument", "A percussion instrument", "A woodwind instrument", "A snack"
		);
		
		answerCounts.increment(questionOneAnswerMap.getAnswer("a"));
		answerCounts.increment(questionOneAnswerMap.getAnswer("b"));
		answerCounts.increment(questionOneAnswerMap.getAnswer("c"));
		answerCounts.increment(questionOneAnswerMap.getAnswer("d"));
		
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
}
