package l2k.trivia.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import l2k.trivia.server.domain.factory.RoomFactory;
import l2k.trivia.server.services.RoomService;
import static l2k.trivia.server.config.Constants.ROOM_NAMES;

@Configuration
@Component
public class TestDataProvider implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired private RoomService roomService;
	@Autowired private RoomFactory roomFactory;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		roomService.addRoom(roomFactory.newRoomWithMascot("Charizard", 1, 15));
		roomService.addRoom(roomFactory.newRoomWithMascot("Garchomp", 2, 15));	
		roomService.addRoom(roomFactory.newRoomWithMascot("Lugia", 3, 15));
		roomService.addRoom(roomFactory.newRoomWithMascot("Rayquaza", 4, 15));
		roomService.addRoom(roomFactory.newRoomWithMascot("Gengar", 5, 15));
		roomService.addRoom(roomFactory.newRoomWithMascot("Mewtwo", 6, 16));
		roomService.addRoom(roomFactory.newRoomWithMascot("Lucario", 7, 15));
		roomService.addRoom(roomFactory.newRoomWithMascot("Blaziken", 8, 15));
		roomService.addRoom(roomFactory.newRoomWithMascot("Eevee", 9, 15));
		roomService.addRoom(roomFactory.newRoomWithMascot("Pikachu", 10, 15));
	}

}
