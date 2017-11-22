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
import static org.mockito.Mockito.*;

import l2k.demo.multiple.chats.game.Player;
import l2k.demo.multiple.chats.game.GameMessageEmitter;
import l2k.demo.multiple.chats.game.Question;
import l2k.demo.multiple.chats.game.QuestionBuilder;
import l2k.demo.multiple.chats.game.TriviaGame;
import l2k.demo.multiple.chats.game.TriviaGameBuilder;
import l2k.demo.multiple.chats.game.TriviaGameMessage;

import name.falgout.jeffrey.testing.junit5.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TriviaGameTest {
	
	private TriviaGame game;
	
	private List<Player> players;
	private List<Question> questions;
	
	@Mock
	private GameMessageEmitter messageEmitter;
	private ArgumentCaptor<TriviaGameMessage> emitterCaptor;
	
	
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
					.addFakeAnswer("A kitchen utensil")
					.build()
			);
			
			add(new QuestionBuilder()
					.setText("What is a snare drum?")
					.setAnswer("A percussion instrument")
					.addFakeAnswer("A brass instrument")
					.addFakeAnswer("A woodwind instrument")
					.addFakeAnswer("A snack")
					.addFakeAnswer("A kitchen utensil")
					.build()
			);
		}};
	}
	
	@BeforeEach
	public void setupEmitterCaptor() {
		emitterCaptor = ArgumentCaptor.forClass(TriviaGameMessage.class);
		doNothing().when(messageEmitter).emitMessage(emitterCaptor.capture());
	}
	
	@BeforeEach
	public void setupGame() {
		game = new TriviaGameBuilder()
				.setPlayers(players)
				.setQuestions(questions)
				.setMessageEmitter(messageEmitter)
				.build();		
	}

	@Test
	public void emitsGameWillStartMessageOnInitialization() {
		TriviaGameMessage message = emitterCaptor.getValue();
		assertEquals(TriviaGameMessage.Header.INITIALIZATION, message.getHeader());
		
		Map<Player, Integer> playerScores = message.getPlayerScores();
		assertEquals((Integer)0, playerScores.get("Tom"));
		assertEquals((Integer)0, playerScores.get("Betty"));
	}

}
