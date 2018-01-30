package l2k.trivia.server.domain.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import l2k.trivia.server.domain.Room;
import l2k.trivia.server.domain.chat.Chat;
import l2k.trivia.server.services.NameGenerator;

@Component
public class RoomFactory {
	
	@Autowired private ChatFactory chatFactory;
	@Autowired private NameGenerator nameGenerator;
	
	@Bean
	public Room newRoom() {
		Room room = new Room(nameGenerator.newRoomName());
		room.setUserCapacity(3);
		
		return room;
	}
	
}
