package l2k.trivia.server.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import l2k.trivia.game.Answer;
import l2k.trivia.server.domain.Room;
import l2k.trivia.server.domain.User;
import l2k.trivia.server.domain.chat.ChatRoomMessage;

@Service
public class RoomService {
	
	private Map<String, Room> rooms;
	private Map<User, Room> usersToRooms; //TODO move this field onto user?
	
	{
		rooms = new HashMap<String, Room>();
		usersToRooms = new HashMap<>();
	}
	
	
	public void clear() { //Temporary for TESTING while DB is not hooked up
		rooms = new HashMap<>();
		usersToRooms = new HashMap<>();
	}
	
	public boolean roomIsFull(String roomName) {
		Room room = rooms.get(roomName);
		return room.isFull();
	}
	
	public void addUserToRoom(String roomName, User user) {
		Room roomManager = rooms.get(roomName);
		roomManager.addUser(user);
		usersToRooms.put(user, roomManager);
	}
	
	
	public void addRoom(Room room) {
		rooms.put(room.getName(), room);
	}
	
	public void addMessageToRoom(String roomName, ChatRoomMessage message) {
		Room roomManager = rooms.get(roomName);
		roomManager.addMessage(message);
	}
	
	public Map<String, Room> getRooms() {
		return MapUtils.unmodifiableMap(rooms);
	}

	public Room getRoom(String roomName) {
		return rooms.get(roomName);
	}

	public boolean userIsInRoom(String roomName, User user) {
		Room room = rooms.get(roomName);
		return room != null && room.contains(user);
	}
	
	public void removeUser(User user) {
		Room room = usersToRooms.get(user);
		room.removeUser(user);
	}

}
