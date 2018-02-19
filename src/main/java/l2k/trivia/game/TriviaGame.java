package l2k.trivia.game;

import static l2k.trivia.game.TriviaGame.GamePhase.ANSWERING_QUESTION;
import static l2k.trivia.game.TriviaGame.GamePhase.BEGIN;
import static l2k.trivia.game.TriviaGame.GamePhase.CHECKING_ANSWERS;
import static l2k.trivia.game.TriviaGame.GamePhase.FINISHED;
import static l2k.trivia.game.TriviaGame.GamePhase.READY;
import static l2k.trivia.game.TriviaGame.GamePhase.WAITING_FOR_PLAYERS;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import l2k.trivia.scheduling.GameScheduler;
import l2k.trivia.server.domain.Room;
import l2k.trivia.server.listeners.GameFinishListener;
import l2k.trivia.server.listeners.GameListener;

public class TriviaGame implements InitializingBean {
	
	enum GamePhase {
		WAITING_FOR_PLAYERS,
		READY,
		BEGIN,
		ANSWERING_QUESTION,
		CHECKING_ANSWERS,
		FINISHED
	}
	
	@Autowired private List<GameListener> listeners;	
	@Autowired private List<GameFinishListener> finishListeners;
	private GamePhase phase = WAITING_FOR_PLAYERS;
	private Map<String, Player> players;
	private Room room;
	private RollCall<TriviaRound> triviaRoundRollCall;
	private TriviaRound currentRound;
	private int minPlayers = 1;
	private int maxPlayers = 3;
	
	
	public TriviaGame(Map<String, Player> players, RollCall<TriviaRound> triviaRoundRollCall, Room room) {
		this.players = players;
		this.triviaRoundRollCall = triviaRoundRollCall;
		this.room = room;
	}
	
	public void addPlayer(Player player) {
		if(players.size() < maxPlayers) players.put(player.getName(), player);
		if(readyForStart()) start();
		notifyListeners();
	}
	
	private boolean readyForStart() {
		return phase == WAITING_FOR_PLAYERS && players.size() >= minPlayers;
	}

	private void start() {
		phase = READY;
		new GameScheduler().schedule(this).execute();
		notifyListeners();
	}
	
	public void announceStart() {
		phase = BEGIN;
		notifyListeners();
	}
	
	public void setupNextRound() {
		phase = ANSWERING_QUESTION;
		currentRound = triviaRoundRollCall.getNextItem();
		notifyListeners();
	}

	public void closeCurrentRound() {
		phase = CHECKING_ANSWERS;
		currentRound.getPlayersWithCorrectAnswer().forEach(Player::incrementScore);
		notifyListeners();
	}
	
	public void closeGame() {
		phase = FINISHED;
		currentRound = null;
		notifyListeners();
	}
	
	public void emitReadyForCleanUp() {
		finishListeners.forEach(GameFinishListener::respondToFinish);
	}

	public void submitAnswer(Player player, Answer answer) {
		if(phase == ANSWERING_QUESTION) {
			currentRound.submitAnswer(player, answer);
			notifyListeners();
		}
	}

	private void notifyListeners() {
		listeners.forEach((listener) -> listener.fireGameUpdate(this));
	}
	
	public boolean isFinished() {
		return triviaRoundRollCall.isFinished();
	}

	public String getRoomName() {
		return room.getName();
	}
	
	public Map<String, Player> getPlayers() {
		return players;
	}
	
	public GamePhase getPhase() {
		return phase;
	}
	
	public int getCurrentRoundNumber() {
		return triviaRoundRollCall.getCurrentItemNumber();
	}
	
	public int getRoundCount() {
		return triviaRoundRollCall.getItemTotal();
	}
	
	public Question getCurrentQuestion() {
		return currentRound != null ? currentRound.getQuestion() : null;
	}
	
	public List<Answer> getCurrentAnswers() {
		return currentRound != null ? currentRound.getAnswers() : null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(readyForStart()) start();		
	}
}
