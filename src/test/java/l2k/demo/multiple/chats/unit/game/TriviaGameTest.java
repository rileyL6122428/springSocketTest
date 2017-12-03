package l2k.demo.multiple.chats.unit.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import l2k.demo.multiple.chats.game.Player;
import l2k.demo.multiple.chats.game.Question;
import l2k.demo.multiple.chats.game.RollCall;
import l2k.demo.multiple.chats.game.ScoreKeeper;
import l2k.demo.multiple.chats.game.TriviaGame;
import l2k.demo.multiple.chats.game.TriviaRound;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import static java.util.Arrays.asList;

@ExtendWith(MockitoExtension.class)
class TriviaGameTest {
	
	private TriviaGame game;
	
	@Mock
	private ScoreKeeper scoreKeeper;
	@Mock
	private RollCall<TriviaRound> triviaRoundRollCall;
	@Mock 
	private TriviaRound firstRound;
	
	@BeforeEach
	public void setup() {
		when(triviaRoundRollCall.getNextItem()).thenReturn(firstRound);
		game = new TriviaGame(scoreKeeper, triviaRoundRollCall);
	}
	
	@Test
	public void setsUpFirstRoundOnInitialization(@Mock Question firstQuestion) {
		verify(triviaRoundRollCall).getNextItem();
		
		when(firstRound.getQuestion()).thenReturn(firstQuestion);
		Question currentQuestion = game.getCurrentQuestion();
		verify(firstRound).getQuestion();
		assertEquals(firstQuestion, currentQuestion);
	}
	
	@Test
	public void delegatesToTheTriviaRoundRollCallWhenReportingTheRoundCount() {
		when(triviaRoundRollCall.getTotalItemCount()).thenReturn(98);
		int roundCount = game.getRoundCount();
		verify(triviaRoundRollCall).getTotalItemCount();
		assertEquals(98, roundCount);
	}
	
	@Test
	public void delegatesToTheQuestionRollWhenReportingTheCompletedRoundCount() {
		when(triviaRoundRollCall.getCompletedItemCount()).thenReturn(555);
		int roundCount = game.getCompletedRoundCount();
		verify(triviaRoundRollCall).getCompletedItemCount();
		assertEquals(555, roundCount);
	}
	
	@Test
	public void delegatesToTheTriviaRoundRollCallWhenReportingIfTheGameIsFinished() {
		when(triviaRoundRollCall.isFinished()).thenReturn(false);
		boolean gameIsFinished = game.isFinished();
		verify(triviaRoundRollCall).isFinished();
		assertFalse(gameIsFinished);
	}
	
	@Test
	public void closeCurrentRoundIncrementsScoreOfUsersWithCorrectAnswers(@Mock Player player1, @Mock Player player2) {
		when(firstRound.getPlayersWithCorrectAnswer()).thenReturn(asList(player1, player2));
		game.closeCurrentRound();
		verify(scoreKeeper).incrementScore(player1);
		verify(scoreKeeper).incrementScore(player2);
	}
	
	@Test
	public void closeCurrentRoundGetsNextRoundByDelegatingToTheRoundFactory(@Mock TriviaRound nextRound, @Mock Question nextQuestion) {
		when(triviaRoundRollCall.getNextItem()).thenReturn(nextRound);
		game.closeCurrentRound();
		verify(triviaRoundRollCall, times(2)).getNextItem();
		
		when(nextRound.getQuestion()).thenReturn(nextQuestion);
		Question currentQuestion = game.getCurrentQuestion();
		verify(nextRound).getQuestion();
		assertEquals(nextQuestion, currentQuestion);
	}
}
