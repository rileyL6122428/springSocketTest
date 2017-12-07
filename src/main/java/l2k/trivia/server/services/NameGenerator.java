package l2k.trivia.server.services;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

@Service
public class NameGenerator {
	
	private Faker faker;
	
	{
		faker = new Faker();
	}
	
	public String getName() {
		return faker.name().firstName();
	}

}
