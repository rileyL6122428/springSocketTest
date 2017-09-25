package l2k.demo.multiple.chats.domain;

import java.util.HashMap;
import java.util.Map;

public class RoomMonitor {
	
	private Map<String, Room> rooms;
	
	{
		rooms = new HashMap<String, Room>();
	}
	
	public void addUserToRoom(String roomName, User user) {
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
	
}
