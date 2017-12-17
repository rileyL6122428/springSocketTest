package l2k.trivia.e2e.server.controller;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerTestUtil {

	public static <T> T parseJson(String jsonString, Class<T> clazz) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(jsonString, clazz);
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String toJson(Object object) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(object);
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
