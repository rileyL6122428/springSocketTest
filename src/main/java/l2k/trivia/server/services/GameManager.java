package l2k.trivia.server.services;

import static l2k.trivia.scheduling.UnitsOfTime.Milliseconds.FIVE_SECONDS;
import static l2k.trivia.scheduling.UnitsOfTime.Milliseconds.ONE_SECOND;
import static l2k.trivia.scheduling.UnitsOfTime.Milliseconds.THREE_SECONDS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import l2k.trivia.game.Answer;
import l2k.trivia.game.Player;
import l2k.trivia.game.SampleTriviaGameBuilder;
import l2k.trivia.game.TriviaGame;
import l2k.trivia.scheduling.DelayedEvent;
import l2k.trivia.scheduling.SequenceBuilder;
import l2k.trivia.server.domain.User;

public class GameManager {
	
	private TriviaGame triviaGame;
	private Map<String, Player> namesToPlayers;
	private RoomMessagingTemplate roomMessagingTemplate;
	private int playerCountRequiredToStartGame = 3;
	private String roomName;
	private GameMessageFactory gameMessageFactory = new GameMessageFactory();
	
	public GameManager(String roomName, RoomMessagingTemplate roomMessagingTemplate) {
		this.roomName = roomName;
		this.roomMessagingTemplate = roomMessagingTemplate;
	}
	
	{
		namesToPlayers = new HashMap<String, Player>();
	}
	
	public void addPlayer(Player player) {
		namesToPlayers.put(player.getName(), player);
		
		if(namesToPlayers.size() >= playerCountRequiredToStartGame) {
			startNewGame();
		}
	}
	
	private void startNewGame() {
		triviaGame = new SampleTriviaGameBuilder()
				.setPlayers(new ArrayList<Player>(namesToPlayers.values()))
				.build();
		
		new SequenceBuilder()
			.addEvent(new DelayedEvent(this::emitReadyForNewGame, ONE_SECOND))
			.addEvent(new DelayedEvent(this::emitGameStart, THREE_SECONDS))
			.addEvent(new DelayedEvent(this::emitGameQuestion, THREE_SECONDS))
			.addEvent(new DelayedEvent(this::emitGameQuestionClose, FIVE_SECONDS))
			.build()
			.execute();
	}
	
	private void emitReadyForNewGame() {
		roomMessagingTemplate.sendGameMessageToRoom(roomName, gameMessageFactory.newGameReadyMessage(triviaGame));
	}
	
	private void emitGameStart() {
		roomMessagingTemplate.sendGameMessageToRoom(roomName, gameMessageFactory.newGameStartMessage(triviaGame));
	}
	
	private void emitGameQuestion() {
		roomMessagingTemplate.sendGameMessageToRoom(roomName, gameMessageFactory.newGameQuestionMessage(triviaGame));
	}
	
	private void emitGameQuestionClose() {
		triviaGame.closeCurrentRound();
		roomMessagingTemplate.sendGameMessageToRoom(roomName, gameMessageFactory.newGameQuestionCloseMessage(triviaGame));
	}

	public void removePlayer(Player player) {
		namesToPlayers.remove(player.getName());
	}

	public void submitAnswer(User user, Answer answer) {
		triviaGame.submitAnswer(user, answer);
	}
	
}
