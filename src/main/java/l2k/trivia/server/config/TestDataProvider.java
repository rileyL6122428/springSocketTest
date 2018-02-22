package l2k.trivia.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import l2k.trivia.server.domain.factory.RoomFactory;
import l2k.trivia.server.services.RoomService;

@Configuration
@Component
public class TestDataProvider implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired private RoomService roomService;
	@Autowired private RoomFactory roomFactory;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		roomService.addRoom(roomFactory.newRoom());
		roomService.addRoom(roomFactory.newRoom());
		roomService.addRoom(roomFactory.newRoom());
		roomService.addRoom(roomFactory.newRoom());
		roomService.addRoom(roomFactory.newRoom());
		roomService.addRoom(roomFactory.newRoom());
	}

}
