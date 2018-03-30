package l2k.trivia.server.domain.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import l2k.trivia.server.domain.Pokemon;
import l2k.trivia.server.domain.Room;

@Configuration
@Component
public class RoomFactory {
	
	@Bean
	@Scope("prototype")
	public Room newRoomWithMascot(String pokemonName, int matchmakingOrder, int userCapacity) {
		Room room = new Room(pokemonName);
		room.setMascot(new Pokemon(pokemonName));
		room.setUserCapacity(userCapacity);
		room.setMatchmakingOrder(matchmakingOrder);
		return room;
	}
	
}
