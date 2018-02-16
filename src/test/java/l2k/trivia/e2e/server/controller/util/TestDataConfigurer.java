package l2k.trivia.e2e.server.controller.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import l2k.trivia.server.domain.Room;
import l2k.trivia.server.services.RoomService;
import l2k.trivia.server.services.UserService;

@Component
public class TestDataConfigurer {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoomService roomMonitor;
	
	public void clearAndResetData() {
		userService.clear();
		roomMonitor.clear();
	}
	
	public void addRoomToApp(Room room) {
		roomMonitor.addRoom(room);
	}
	
}
