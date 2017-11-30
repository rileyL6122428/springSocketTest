package l2k.demo.multiple.chats.unit.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import l2k.demo.multiple.chats.game.QuestionRoll;
import l2k.demo.multiple.chats.game.ScoreKeeper;
import l2k.demo.multiple.chats.game.TriviaGame;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TriviaGameTest {
	
	private TriviaGame game;

	@Mock
	private ScoreKeeper scoreKeeper;
	@Mock
	private QuestionRoll questionRoll;
	
	
	@BeforeEach
	public void setup() {
		game = new TriviaGame(scoreKeeper, questionRoll);
	}
	
	@Test
	public void delegatesToTheQuestionRollWhenReportingTheQuestionCount() {
		when(questionRoll.getQuestionCount()).thenReturn(98);
		int questionCount = game.getQuestionCount();
		verify(questionRoll).getQuestionCount();
		assertEquals(98, questionCount);
	}
	
	@Test
	public void delegatesToTheQuestionRollWhenReportingTheCompletedQuestionCount() {
		when(questionRoll.getCompletedQuestionCount()).thenReturn(555);
		int questionCount = game.getCompletedQuestionCount();
		verify(questionRoll).getCompletedQuestionCount();
		assertEquals(555, questionCount);
	}
	
	@Test
	public void delegatesToTheQuestionRollWhenReportingIfTheGameIsFinished() {
		when(questionRoll.isFinished()).thenReturn(false);
		boolean gameIsFinished = game.isFinished();
		verify(questionRoll).isFinished();
		assertFalse(gameIsFinished);
	}
}
