package l2k.trivia.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

public class AdditionalAssertions {
	
	public static void assertIsUUID(String string) {
		try {
			UUID.fromString(string);			
		} catch (Exception exception) {
			fail("trivia session cookie value is not an instance of UUID");
		}
	}
	
}
