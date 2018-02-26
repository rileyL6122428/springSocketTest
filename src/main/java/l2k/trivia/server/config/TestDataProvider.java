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
//		for(String roomName : ROOM_NAMES) {
//		};
		roomService.addRoom(roomFactory.newRoomWithMascot("Charizard", 1));
		roomService.addRoom(roomFactory.newRoomWithMascot("Garchomp", 2));	
		roomService.addRoom(roomFactory.newRoomWithMascot("Lugia", 3));
		roomService.addRoom(roomFactory.newRoomWithMascot("Rayquaza", 4));
		roomService.addRoom(roomFactory.newRoomWithMascot("Gengar", 5));
		roomService.addRoom(roomFactory.newRoomWithMascot("Mewtwo", 6));
		roomService.addRoom(roomFactory.newRoomWithMascot("Lucario", 7));
		roomService.addRoom(roomFactory.newRoomWithMascot("Blaziken", 8));
		roomService.addRoom(roomFactory.newRoomWithMascot("Eevee", 9));
		roomService.addRoom(roomFactory.newRoomWithMascot("Pikachu", 10));
		
		
//		roomService.addRoom(roomFactory.newRoomWithMascot(roomName));			
//		roomService.addRoom(roomFactory.newRoom());
//		roomService.addRoom(roomFactory.newRoom());
//		roomService.addRoom(roomFactory.newRoom());
//		roomService.addRoom(roomFactory.newRoom());
//		roomService.addRoom(roomFactory.newRoom());
	}

}
