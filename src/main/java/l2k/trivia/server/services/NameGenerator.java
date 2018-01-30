package l2k.trivia.server.services;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

@Service
public class NameGenerator {
	
	private Faker faker;
	
	{
		faker = new Faker();
	}
	
	public String newName() {
		return faker.name().firstName();
	}
	
	public String newRoomName() {
		return faker.pokemon().name() + " Room";
	}

}
