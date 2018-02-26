package l2k.trivia.server.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import l2k.trivia.game.Answer;
import l2k.trivia.game.Player;
import l2k.trivia.game.TriviaGame;
import l2k.trivia.game.TriviaGameFactory;
import l2k.trivia.server.domain.chat.Chat;
import l2k.trivia.server.domain.chat.ChatRoomMessage;
import l2k.trivia.server.domain.chat.JoinRoomMessage;
import l2k.trivia.server.domain.chat.LeaveRoomMessage;
import l2k.trivia.server.domain.factory.ChatFactory;
import l2k.trivia.server.listeners.JoinRoomListener;
import l2k.trivia.server.listeners.LeaveRoomListener;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Room implements InitializingBean {
	
	@Autowired private List<JoinRoomListener> joinListeners;
	@Autowired private List<LeaveRoomListener> leaveListeners;
	@Autowired private TriviaGameFactory gameFactory;
	@Autowired private ChatFactory chatFactory;
	
	private String name;
	private Map<String, User> users;
	private int userCapacity;
	private Chat chat;
	private TriviaGame game;
	private Pokemon mascot;
	private int matchmakingOrder;
	
	public int getMatchmakingOrder() {
		return matchmakingOrder;
	}

	public void setMatchmakingOrder(int order) {
		this.matchmakingOrder = order;
	}

	public Pokemon getMascot() {
		return mascot;
	}

	public void setMascot(Pokemon mascot) {
		this.mascot = mascot;
	}

	public Room() { }
	
	public Room(String roomName) {
		this.name = roomName;
	}
	
	{
		setUsers(new HashMap<String, User>());		
	}
	
	public boolean isFull() {
		return users.size() >= userCapacity;
	}
	
	public void addMessage(ChatRoomMessage message) {
		chat.addMessage(message);
	}
	
	public void addMessage(User user, String messageBody) {
		chat.addMessage(new ChatRoomMessage(user, messageBody));
	}
	
	public boolean addUser(User user) {
		boolean userAdded = false;
		if(!isFull()) {
			chat.addMessage(new JoinRoomMessage(user));
			users.put(user.getName(), user);
			game.addPlayer(user);
			joinListeners.forEach((listener) -> listener.fireJoinRoomEvent(user, this));
			userAdded = true;
		}
		return userAdded;
	}
	
	public boolean contains(String username) {
		return users.get(username) != null;
	}
	
	public boolean contains(User user) {
		return user != null && contains(user.getName());
	}
	
	public int getTotalNumberOfUsers() {
		return users.size();
	}
	
	public void removeUser(User user) {
		if(contains(user)) {
			addMessage(new LeaveRoomMessage(user));
			users.remove(user.getName());
			leaveListeners.forEach((listener) -> listener.fireLeaveRoomEvent(this));
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserCapacity() {
		return userCapacity;
	}

	public void setUserCapacity(int userCapacity) {
		this.userCapacity = userCapacity;
	}

	public Chat getChat() {
		return chat;
	}
	

	public Map<String, User> getUsers() {
		return users;
	}

	public void setUsers(Map<String, User> users) {
		this.users = users;
	}
	
	public void submitTriviaAnswer(Player player, Answer answer) {
		game.submitAnswer(player, answer);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		game = gameFactory.newTriviaGame(this);
		chat = chatFactory.newChat(this);
	}
	
	public void startNewGame() {
		this.game = gameFactory.newTriviaGame(this);
	}
	
}
