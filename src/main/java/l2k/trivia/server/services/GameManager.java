package l2k.trivia.server.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import l2k.trivia.game.Player;
import l2k.trivia.game.SampleTriviaGameBuilder;
import l2k.trivia.game.TriviaGame;

public class GameManager {
	
	private TriviaGame triviaGame;
	private Map<String, Player> namesToPlayers;
	private RoomMessagingTemplate roomMessagingTemplate;
	private int playerCountRequiredToStartGame = 3;
	private Timer timer;
	private String roomName;
	private GameMessageFactory gameMessageFactory = new GameMessageFactory();
	
	public GameManager(String roomName, RoomMessagingTemplate roomMessagingTemplate) {
		this.roomName = roomName;
		this.roomMessagingTemplate = roomMessagingTemplate;
	}
	
	{
		namesToPlayers = new HashMap<String, Player>();
		timer = new Timer();
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
		
		scheduleTask(this::emitReadyForNewGame, 1000);
		scheduleTask(this::emitGameStart, 4000);
		
		scheduleTask(this::emitGameQuestion, 7000);
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


	public void removePlayer(Player player) {
		namesToPlayers.remove(player.getName());
	}
	
	public void scheduleTask(Runnable runnable, int delay) {
		timer.schedule(new TimerTask() {

			public void run() {
				runnable.run();
			}
			
		}, 3000);
	}
	
	
}
