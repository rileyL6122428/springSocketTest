package l2k.trivia.server.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import l2k.trivia.game.Answer;
import l2k.trivia.server.domain.Room;
import l2k.trivia.server.domain.User;
import l2k.trivia.server.domain.chat.ChatRoomMessage;

@Service
public class RoomMonitor 
//implements InitializingBean 
{
	
	private Map<String, RoomManager> roomManagers;
	private Map<User, RoomManager> usersToRoomManagers; //TODO move this field onto user?
	
	@Autowired
	private RoomMessagingTemplate roomMessagingTemplate;
	
	{
		roomManagers = new HashMap<String, RoomManager>();
		usersToRoomManagers = new HashMap<>();
	}
	
	
	public void clear() { //Temporary for TESTING while DB is not hooked up
		roomManagers = new HashMap<>();
		usersToRoomManagers = new HashMap<>();
	}
	
	public boolean roomIsFull(String roomName) {
		RoomManager roomManager = roomManagers.get(roomName);
		return roomManager.isFull();
	}
	
	public void addUserToRoom(String roomName, User user) {
		RoomManager roomManager = roomManagers.get(roomName);
		roomManager.addUser(user);
		usersToRoomManagers.put(user, roomManager);
	}
	
	
	public void addRoom(Room room) {
		roomManagers.put(room.getName(), new RoomManager(room, roomMessagingTemplate));
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

	public void submitGameAnswer(String roomName, User user, Answer answer) {
		RoomManager roomManager = roomManagers.get(roomName);
		roomManager.submitGameAnswer(user, answer);
	}
	
	public void removeUserFromRoom(String roomName, User user) {
		RoomManager roomManager = usersToRoomManagers.remove(user);
		roomManager.removeUser(user);
	}
	
	public void removeUser(User user) {
		RoomManager roomManager = usersToRoomManagers.remove(user);
		if(roomManager != null) roomManager.removeUser(user);
	}
	
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		roomManagers = new HashMap<String, RoomManager>();
//		usersToRoomManagers = new HashMap<>();
//		
//		//configured for test
//		addRoom(new Room() {{ 
//			setName("ROOM_ONE");
//			setUserCapacity(3);
//		}});
//	}

}
