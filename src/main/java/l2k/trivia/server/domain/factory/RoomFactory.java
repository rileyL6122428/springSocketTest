package l2k.trivia.server.domain.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import l2k.trivia.server.domain.Pokemon;
import l2k.trivia.server.domain.Room;
import l2k.trivia.server.services.NameGenerator;

@Configuration
@Component
public class RoomFactory {
	
	@Autowired private NameGenerator nameGenerator;
	
	@Bean
	@Scope("prototype")
	public Room newRoom() {
		Room room = new Room(nameGenerator.newRoomName());
		room.setUserCapacity(3);
		return room;
	}
	
	@Bean
	@Scope("prototype")
	public Room newRoomWithMascot(String pokemonName, int matchmakingOrder) {
		Room room = new Room(pokemonName);
		room.setMascot(new Pokemon(pokemonName));
		room.setUserCapacity(3);
		room.setMatchmakingOrder(matchmakingOrder);
		return room;
	}
	
}
