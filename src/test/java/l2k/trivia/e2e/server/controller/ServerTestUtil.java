package l2k.trivia.e2e.server.controller;

import java.io.IOException;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.fasterxml.jackson.databind.module.SimpleModule;

import l2k.trivia.server.domain.User;
import l2k.trivia.server.domain.chat.Sender;

public class ServerTestUtil {

	public static <T> T parseJson(String jsonString, Class<T> clazz) {
		T parsedObject = null;
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(getResolverModule());
			parsedObject = mapper.readValue(jsonString, clazz);
			
		} catch (IOException e) { e.printStackTrace(); }
		
		return parsedObject;
	}
	
	private static SimpleModule getResolverModule() {
		SimpleModule module = new SimpleModule("ResolverModule", Version.unknownVersion());
		module.setAbstractTypes(getSenderResolver());
		return module;
	}
	
	private static SimpleAbstractTypeResolver getSenderResolver() {
		SimpleAbstractTypeResolver senderResolver = new SimpleAbstractTypeResolver();
		senderResolver.addMapping(Sender.class, User.class);
		return senderResolver;
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
