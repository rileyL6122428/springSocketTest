package l2k.trivia.server.domain.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import l2k.trivia.server.domain.chat.Chat;

@Configuration
@Component
public class ChatFactory {

	@Bean
	public Chat newChat() {
		return new Chat();
	}

}
