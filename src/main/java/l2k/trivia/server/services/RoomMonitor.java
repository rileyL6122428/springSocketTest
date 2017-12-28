package l2k.trivia.server.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import l2k.trivia.server.domain.ChatRoomMessage;
import l2k.trivia.server.domain.Room;
import l2k.trivia.server.domain.User;

@Service
public class RoomMonitor {
	
//	private Map<String, Room> rooms;
	private Map<String, RoomManager> roomManagers;
	
	{
//		rooms = new HashMap<String, Room>();
		roomManagers = new HashMap<String, RoomManager>();
		
		//configured for test
		addRoom(new Room() {{ 
			setName("ROOM_ONE");
			setMaxNumberOfUsers(3);
		}});
		
	}
	
	public void clear() { //Temporary for TESTING while DB is not hooked up
//		rooms = new HashMap<String, Room>();
		roomManagers = new HashMap<String, RoomManager>();
	}
	
	public boolean roomIsFull(String roomName) {
		RoomManager roomManager = roomManagers.get(roomName);
		return roomManager.isFull();
	}
	
	public void addUserToRoom(String roomName, User user) {
		RoomManager roomManager = roomManagers.get(roomName);
		roomManager.addUser(user);
	}
	
	public void removeUserFromRoom(String roomName, User user) {
		RoomManager roomManager = roomManagers.get(roomName);
		roomManager.removeUser(user);
	}
	
	public void addRoom(Room room) {
		roomManagers.put(room.getName(), new RoomManager(room));
	}
	
	public void addMessageToRoom(String roomName, ChatRoomMessage message) {
		RoomManager roomManager = roomManagers.get(roomName);
		roomManager.addMessage(message);
	}
	
	public Map<String, Room> getRooms() {
		Map<String, Room> rooms = new HashMap<String, Room>();
		
		for(Map.Entry<String, RoomManager> nameToRoomManager : roomManagers.entrySet()) {
			String roomName = nameToRoomManager.getKey(); 
			RoomManager roomManager = nameToRoomManager.getValue();
			rooms.put(roomName, roomManager.getRoom());
		}
		
		return rooms;
	}

	public Room getRoom(String roomName) {
		RoomManager roomManager = roomManagers.get(roomName);
		return roomManager.getRoom();
	}

	public boolean userIsInRoom(String roomName, User user) {
		RoomManager roomManager = roomManagers.get(roomName);
		return roomManager != null && roomManager.contains(user);
	}

	
}
