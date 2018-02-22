package l2k.trivia.server.domain.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import l2k.trivia.server.domain.Room;
import l2k.trivia.server.domain.chat.Chat;

@Configuration
@Component
public class ChatFactory {

	@Bean
	@Scope("prototype")
	public Chat newChat(Room room) {
		return new Chat(room);
	}

}
