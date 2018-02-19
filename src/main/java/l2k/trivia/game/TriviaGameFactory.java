package l2k.trivia.game;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import l2k.trivia.server.domain.Room;

@Configuration
@Component
public class TriviaGameFactory {
	
	@Bean
	@Scope("prototype")
	public TriviaGame newTriviaGame(Room room) {
		return new SampleTriviaGameBuilder().setRoom(room).build();
	}
	
}
