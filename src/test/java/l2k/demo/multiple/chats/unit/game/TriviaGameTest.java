package l2k.demo.multiple.chats.unit.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import l2k.demo.multiple.chats.game.Player;
import l2k.demo.multiple.chats.game.Question;
import l2k.demo.multiple.chats.game.QuestionRoll;
import l2k.demo.multiple.chats.game.ScoreKeeper;
import l2k.demo.multiple.chats.game.TriviaGame;
import l2k.demo.multiple.chats.game.TriviaRound;
import l2k.demo.multiple.chats.game.TriviaRoundFactory;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import static java.util.Arrays.asList;

@ExtendWith(MockitoExtension.class)
class TriviaGameTest {
	
	private TriviaGame game;
	
	@Mock
	private ScoreKeeper scoreKeeper;
	@Mock
	private QuestionRoll questionRoll;
	@Mock
	private TriviaRoundFactory triviaRoundFactory;
	@Mock
	private Question firstQuestion;
	@Mock 
	private TriviaRound firstRound;
	
	@BeforeEach
	public void setup() {
		when(questionRoll.getNextQuestion()).thenReturn(firstQuestion);
		when(triviaRoundFactory.buildTriviaRound(any())).thenReturn(firstRound);
		game = new TriviaGame(scoreKeeper, questionRoll, triviaRoundFactory);
	}
	
	@Test
	public void setsUpFirstQuestionOnInitialization() {
		verify(triviaRoundFactory).buildTriviaRound(firstQuestion);		
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
	
	@Test
	public void closeCurrentQuestionIncrementsScoreOfUsersWithCorrectAnswers(@Mock Player player1, @Mock Player player2) {
		when(firstRound.getPlayersWithCorrectAnswer()).thenReturn(asList(player1, player2));
		game.closeCurrentQuestion();
		verify(scoreKeeper).incrementScore(player1);
		verify(scoreKeeper).incrementScore(player2);
	}
	
}
