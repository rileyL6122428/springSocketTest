package l2k.trivia.server.controllers.wsmessages;

import java.util.List;
import java.util.Map;

import l2k.trivia.game.Answer;
import l2k.trivia.game.Player;
import l2k.trivia.game.Question;
import l2k.trivia.game.TriviaGame;

public class GameMessage {
	
	protected String typeHeader;
	protected Map<Player, Integer> playerScores;
	protected int totalRoundCount;
	protected int completedRoundCount;
	protected Question currentQuestion;
	protected List<Answer> currentQuestionAnswers;
	
	public GameMessage(TriviaGame triviaGame) {
		playerScores = triviaGame.getPlayerScores();
		totalRoundCount = triviaGame.getRoundCount();
		completedRoundCount = triviaGame.getCompletedRoundCount();
		currentQuestion = triviaGame.getCurrentQuestion();
		currentQuestionAnswers = triviaGame.getCurrentQuestionAnswers();
	}

	public String getTypeHeader() {
		return typeHeader;
	}

	public void setTypeHeader(String typeHeader) {
		this.typeHeader = typeHeader;
	}

	public Map<Player, Integer> getPlayerScores() {
		return playerScores;
	}

	public void setPlayerScores(Map<Player, Integer> playerScores) {
		this.playerScores = playerScores;
	}

	public int getTotalRoundCount() {
		return totalRoundCount;
	}

	public void setTotalRoundCount(int totalRoundCount) {
		this.totalRoundCount = totalRoundCount;
	}

	public int getCompletedRoundCount() {
		return completedRoundCount;
	}

	public void setCompletedRoundCount(int completedRoundCount) {
		this.completedRoundCount = completedRoundCount;
	}

	public Question getCurrentQuestion() {
		return currentQuestion;
	}

	public void setCurrentQuestion(Question currentQuestion) {
		this.currentQuestion = currentQuestion;
	}

	public List<Answer> getCurrentQuestionAnswers() {
		return currentQuestionAnswers;
	}

	public void setCurrentQuestionAnswers(List<Answer> currentQuestionAnswers) {
		this.currentQuestionAnswers = currentQuestionAnswers;
	}
	
}
