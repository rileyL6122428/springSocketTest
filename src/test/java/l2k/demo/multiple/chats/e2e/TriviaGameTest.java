package l2k.demo.multiple.chats.e2e;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

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
	
	@Mock
	private GameMessageEmitter messageEmitter;
	private ArgumentCaptor<TriviaGameMessage> emitterCaptor;
	
	private TriviaGame game;
	
	@BeforeEach
	public void setup() {
		List<Player> players = new ArrayList<Player>() {{
			add(new Player("Tom"));
			add(new Player("Betty"));
		}};
		
		List<Question> quesitons = new ArrayList<Question>() {{
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
		
		game = new TriviaGameBuilder()
				.setPlayers(players)
				.setQuestions(quesitons)
				.setTicksPerQuestion(5)
				.setMessageEmitter(messageEmitter)
				.build();
		
		emitterCaptor = ArgumentCaptor.forClass(TriviaGameMessage.class);
		doNothing().when(messageEmitter).emitMessage(emitterCaptor.capture());
	}

	@Disabled
	@Test
	public void emitsStatsOnInitialization() {
		
	}

}
