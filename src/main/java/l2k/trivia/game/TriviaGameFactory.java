package l2k.trivia.game;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import l2k.trivia.server.domain.Room;

@Configuration
@Component
public class TriviaGameFactory {
	
	@Bean
	public TriviaGame newTriviaGame(Room room) {
		return new SampleTriviaGameBuilder().setRoom(room).build();
	}
	
}
