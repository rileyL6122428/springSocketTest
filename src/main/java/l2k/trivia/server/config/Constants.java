package l2k.trivia.server.config;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

import java.util.Collections;
import java.util.List;

public class Constants {
	
	public static final List<String> ROOM_NAMES = unmodifiableList(asList(
		"Charizard",
		"Garchomp",
		"Lugia",
		"Rayquaza",
		"Gengar",
		"Mewtwo",
		"Lucario",
		"Blaziken",
		"Eevee",
		"Pikachu"
	));  
	
	public static class STOMP {
		
		public static class DestinationVariables {
			public static final String ROOM_NAME = "roomName";
		}
		
		public static class PathPrefixes {			
			public static final String BROKER_TOPIC = "/topic";
			public static final String APP_MESSAGING = "/app";
			public static final String ROOM = "/room/{" + DestinationVariables.ROOM_NAME + "}";
			public static final String GAME = ROOM + "/game";
		}
		
		public static class Endpoints {
			public static final String SESSION_CONNECTION = "/trivia-sockets";
			public static final String MATCHMAKING_SUBSCRIPTION = "/matchmaking";
			public static final String SEND = "/send-message";
			public static final String CHAT = "/chat";
			public static final String SUBMIT_ANSWER = "/submit-answer";
		}
		
		public static class MessageHeaders {
			public static final String ROOM_NAME = "roomName";
			public static final String ROOM = "ROOM";
			public static final String USER = "USER";
			public static final String SESSION_ID = "SESSION_ID";
		}
	}
	
	public static class HTTP {
		
		public static class PathVariables {
			public static final String ROOM_NAME = "roomName";
		}
		
		public static class PathPrefixes {
			public static final String ROOM = "/room/{" + PathVariables.ROOM_NAME + "}";
			
		}
		
		public static class Endpoints {
			public static final String APP_ROOT = "/";
			public static final String SESSION = "/session";
			public static final String MATCHMAKING_STATS = "/matchmaking/stats";
			public static final String JOIN = "/join";
			public static final String LEAVE = "/leave";
			public static final String CHAT = "/chat";
			public static final String USER = "/user";
		}
		
		public static class RequestAttribute {
			public static final String USER = "USER";
			public static final String ROOM = "ROOM";
			public static final String CHAT = "CHAT";
		}
		
	}
	
	public static class Cookies {
		public static final String SESSION_ID = "TRIVIA_SESSION_COOKIE";
	}
	
	public static class Session {
		public static final String ID = "ID";
	}
	
	public static class BeanDefinitions {
		public static final String SESSION_TO_USER_MAP = "SESSION_TO_USER_MAP";
		public static final String USER_NAMES = "USER_NAMES";
	}
	
	public static class StaticContent {
		public static final String TRIVIA_CLIENT = "index.html";
	}
	
	public static final List<String> USER_NAMES = unmodifiableList(asList(
		"Red",
		"Blue",
		"Ethan",
		"Silver",
		"Kris",
		"Brock",
		"Misty",
		"Erika",
		"Blaine",
		"Giovanni",
		"Koga",
		"Lt. Surge",
		"Sabrina",
		"Janine",
		"Falkner",
		"Bugsy",
		"Whitney",
		"Morty",
		"Chuck",
		"Jasmine",
		"Pryce",
		"Clair",
		"Agatha",
		"Bruno",
		"Lance",
		"Lorelei",
		"Will",
		"Karen",
		"Copycat",
		"Old man",
		"Mr. Fuji",
		"Safari Zone Warden",
		"Primo",
		"Celio",
		"Lostelle",
		"Magikarp Salesman",
		"Name Rater",
		"Felicity",
		"Professor Birch",
		"Wally",
		"May",
		"DJ Mary",
		"Dude",
		"Fossil Maniac",
		"Bill",
		"Todd S.",
		"Joy",
		"Jenny",
		"MissingNo"
	));
	
	
	
}
