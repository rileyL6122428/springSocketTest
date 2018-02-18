package l2k.trivia.game;

import static l2k.trivia.game.TriviaGame.GamePhase.ANSWERING_QUESTION;
import static l2k.trivia.game.TriviaGame.GamePhase.BEGIN;
import static l2k.trivia.game.TriviaGame.GamePhase.CHECKING_ANSWERS;
import static l2k.trivia.game.TriviaGame.GamePhase.FINISHED;
import static l2k.trivia.game.TriviaGame.GamePhase.READY;
import static l2k.trivia.game.TriviaGame.GamePhase.WAITING_FOR_PLAYERS;
import static l2k.trivia.scheduling.UnitsOfTime.Milliseconds.FIVE_SECONDS;
import static l2k.trivia.scheduling.UnitsOfTime.Milliseconds.THREE_SECONDS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import l2k.trivia.scheduling.DelayedEvent;
import l2k.trivia.scheduling.GameScheduler;
import l2k.trivia.scheduling.SequenceBuilder;
import l2k.trivia.server.domain.Room;
import l2k.trivia.server.listeners.GameListener;

public class TriviaGame {
	
	enum GamePhase {
		WAITING_FOR_PLAYERS,
		READY,
		BEGIN,
		ANSWERING_QUESTION,
		CHECKING_ANSWERS,
		FINISHED
	}
	
	@Autowired private List<GameListener> listeners;	
	private GamePhase phase = WAITING_FOR_PLAYERS;
	private Map<String, Player> players = new HashMap<String, Player>();
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
		if(phase != WAITING_FOR_PLAYERS) return;
		if(players.size() < maxPlayers) players.put(player.getName(), player);
		if(players.size() >= minPlayers) start();
		notifyListeners();
	}

	private void start() {
		phase = READY;
		new GameScheduler().schedule(this).execute();
		notifyListeners();
//		SequenceBuilder sequenceBuilder = new SequenceBuilder();
//		
//		sequenceBuilder.addEvent(new DelayedEvent(this::announceStart, THREE_SECONDS));			
//		
//		for(int counter = 1; counter <= triviaRoundRollCall.getItemTotal(); counter++) {
//			sequenceBuilder.addEvent(new DelayedEvent(this::setupNextRound, THREE_SECONDS));
//			sequenceBuilder.addEvent(new DelayedEvent(this::closeCurrentRound, FIVE_SECONDS));			
//		}
//		
//		sequenceBuilder.addEvent(new DelayedEvent(this::closeGame, THREE_SECONDS));
//			
//		sequenceBuilder.build().execute();
	}
	
	public void announceStart() {
		phase = BEGIN;
		notifyListeners();
	}
	

	public void closeCurrentRound() {
		phase = CHECKING_ANSWERS;
		currentRound.getPlayersWithCorrectAnswer().forEach(Player::incrementScore);
		notifyListeners();
	}

	public void setupNextRound() {
		phase = ANSWERING_QUESTION;
		currentRound = triviaRoundRollCall.getNextItem();
		notifyListeners();
	}
	
	public void closeGame() {
		phase = FINISHED;
		currentRound = null;
		notifyListeners();
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
	
	public void setPlayers(Map<String, Player> players) {
		this.players = players;
	}
	
	public GamePhase getPhase() {
		return phase;
	}
	
	public void setPhase(GamePhase phase) {
		this.phase = phase;
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
}
