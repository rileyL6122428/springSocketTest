package l2k.demo.multiple.chats.e2e;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

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
		
		game.nextQuestion();
		assertEquals(2, game.getQuestionCount());
		assertEquals(1, game.getCompletedQuestionCount());
		
		game.nextQuestion();
		assertEquals(2, game.getQuestionCount());
		assertEquals(2, game.getCompletedQuestionCount());
		assertTrue(game.isFinished());
	}
	
	@Disabled
	@Test
	public void asksQuestionsInTheOrderProvided() {
//		String questionText = game.getCurrentQuestion();
//		assertEquals()
	}

}
