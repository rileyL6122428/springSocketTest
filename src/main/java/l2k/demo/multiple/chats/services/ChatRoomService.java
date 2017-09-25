package l2k.demo.multiple.chats.services;

import org.springframework.stereotype.Service;

import l2k.demo.multiple.chats.domain.Room;
import l2k.demo.multiple.chats.domain.RoomMonitor;

@Service
public class ChatRoomService {
	
	private RoomMonitor roomMonitor;
	
	{
		roomMonitor = new RoomMonitor();
		
		roomMonitor.addRoom(new Room() {{ setName("ROOM_ONE"); }});
		roomMonitor.addRoom(new Room() {{ setName("ROOM_TWO"); }});
	}
	
}
