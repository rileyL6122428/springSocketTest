package l2k.demo.multiple.chats.services;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import l2k.demo.multiple.chats.domain.Room;
import l2k.demo.multiple.chats.domain.User;

@Service
public class RoomMonitor {
	
	private Map<String, Room> rooms;
	
	{
		rooms = new HashMap<String, Room>();
		
		//configured for test
		addRoom(new Room() {{ 
			setName("ROOM_ONE");
			setMaxNumberOfUsers(3);
		}});
		
		addRoom(new Room() {{ 
			setName("ROOM_TWO");
			setMaxNumberOfUsers(3);
		}});
	}
	
	public boolean roomIsFull(String roomName) {
		Room room = rooms.get(roomName);
		return room.isFull();
	}
	
	public void addUserToRoom(String roomName, Principal user) {
		Room room = rooms.get(roomName);
		room.addUser(user);
	}
	
	public void removeUserFromRoom(String roomName, User user) {
		Room room = rooms.get(roomName);
		room.removeUser(user);
	}
	
	public void addRoom(Room room) {
		rooms.put(room.getName(), room);
	}
	
	public void removeRoom(String roomName) {
		rooms.remove(roomName);
	}
	
	public void removeRoom(Room room) {
		rooms.remove(room.getName());
	}
	
	public Map<String, Room> getRooms() {
		return rooms;
	}

	public Room getRoom(String roomName) {
		return rooms.get(roomName);
	}
	
}
