package l2k.trivia.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import l2k.trivia.server.domain.Room;
import l2k.trivia.server.domain.chat.Chat;
import l2k.trivia.server.domain.factory.ChatFactory;
import l2k.trivia.server.domain.factory.RoomFactory;
import l2k.trivia.server.services.RoomMonitor;

@Configuration
@Component
public class TestDataProvider implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired private RoomMonitor roomMonitor;
	@Autowired private RoomFactory roomFactory;
	@Autowired private ChatFactory chatFactory;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Chat chat = newChat();
		Room room = newRoom();
		
		chat.setRoom(room);
		room.setChat(chat);
		
		roomMonitor.addRoom(room);
	}
	
	@Bean
	public Chat newChat() {
		return chatFactory.newChat();
	}
	
	@Bean
	public Room newRoom() {
		return roomFactory.newRoom();
	}

}
