package l2k.trivia.server.services;

import static l2k.trivia.scheduling.UnitsOfTime.Milliseconds.FIVE_SECONDS;
import static l2k.trivia.scheduling.UnitsOfTime.Milliseconds.ONE_SECOND;
import static l2k.trivia.scheduling.UnitsOfTime.Milliseconds.THREE_SECONDS;

import java.util.ArrayList;
import java.util.Map;

import l2k.trivia.game.Answer;
import l2k.trivia.game.Player;
import l2k.trivia.game.SampleTriviaGameBuilder;
import l2k.trivia.game.TriviaGame;
import l2k.trivia.scheduling.DelayedEvent;
import l2k.trivia.scheduling.Sequence;
import l2k.trivia.scheduling.SequenceBuilder;
import l2k.trivia.server.domain.User;

public class GameManager {
	
	private TriviaGame triviaGame;
	private Map<String, Player> namesToPlayers;
	private RoomMessagingTemplate roomMessagingTemplate;
	private int minPlayers;
	private String roomName;
	private GameMessageFactory gameMessageFactory = new GameMessageFactory();
	
	public GameManager(String roomName, RoomMessagingTemplate roomMessagingTemplate, int minPlayers, Map<String, Player> namesToPlayers) {
		this.roomName = roomName;
		this.roomMessagingTemplate = roomMessagingTemplate;
		this.minPlayers = minPlayers;
		this.namesToPlayers = namesToPlayers;
	}
	
	public void addPlayer(Player player) {
		namesToPlayers.put(player.getName(), player);
		
		if(namesToPlayers.size() >= minPlayers)
			startNewGame();
	}
	
	private void startNewGame() {
		triviaGame = newSampleTriviaGame();
		
		Sequence gameSequence = newGameSequence();
		gameSequence.execute();
	}
	
	private TriviaGame newSampleTriviaGame() {
		return new SampleTriviaGameBuilder()
				.setPlayers(new ArrayList<Player>(namesToPlayers.values()))
				.build();
	}
	
	private Sequence newGameSequence() {
		SequenceBuilder sequenceBuilder = new SequenceBuilder();
		
		sequenceBuilder.addEvent(new DelayedEvent(this::emitReadyForNewGame, ONE_SECOND));
		sequenceBuilder.addEvent(new DelayedEvent(this::emitGameStart, THREE_SECONDS));
		sequenceBuilder.addEvent(new DelayedEvent(this::emitFirstGameQuestion, THREE_SECONDS));
		sequenceBuilder.addEvent(new DelayedEvent(this::emitGameQuestionClose, FIVE_SECONDS));			
		
		for(int counter = 2; counter <= triviaGame.getRoundCount(); counter++) {
			sequenceBuilder.addEvent(new DelayedEvent(this::emitGameQuestion, THREE_SECONDS));
			sequenceBuilder.addEvent(new DelayedEvent(this::emitGameQuestionClose, FIVE_SECONDS));			
		}
		
		sequenceBuilder.addEvent(new DelayedEvent(this::emitGameClose, THREE_SECONDS));
			
		return sequenceBuilder.build();
	}
	
	private void emitReadyForNewGame() {
		roomMessagingTemplate.sendGameMessageToRoom(roomName, gameMessageFactory.newGameReadyMessage(triviaGame));
	}
	
	private void emitGameStart() {
		roomMessagingTemplate.sendGameMessageToRoom(roomName, gameMessageFactory.newGameStartMessage(triviaGame));
	}
	
	private void emitFirstGameQuestion() {
		roomMessagingTemplate.sendGameMessageToRoom(roomName, gameMessageFactory.newGameQuestionMessage(triviaGame));
	}
	
	private void emitGameQuestion() {
		triviaGame.setupNextRound();
		roomMessagingTemplate.sendGameMessageToRoom(roomName, gameMessageFactory.newGameQuestionMessage(triviaGame));
	}
	
	private void emitGameQuestionClose() {
		triviaGame.closeCurrentRound();
		roomMessagingTemplate.sendGameMessageToRoom(roomName, gameMessageFactory.newGameQuestionCloseMessage(triviaGame));
	}
	
	private void emitGameClose() {
		roomMessagingTemplate.sendGameMessageToRoom(roomName, gameMessageFactory.newGameCloseMessage(triviaGame));
	}

	public void removePlayer(Player player) {
		namesToPlayers.remove(player.getName());
		triviaGame.removePlayer(player);
	}

	public void submitAnswer(User user, Answer answer) {
		triviaGame.submitAnswer(user, answer);
	}
	
}
