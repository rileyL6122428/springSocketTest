package l2k.trivia.server.config;

public class Constants {
	
	
	public static class STOMP {
		
		public static class PathVariables {
			public static final String ROOM_NAME = "roomName";
		}
		
		public static class PathPrefixes {			
			public static final String BROKER_TOPIC = "/topic";
			public static final String APP_MESSAGING = "/app";
			public static final String BROKER_SUBSCRIPTION_LISTENING = BROKER_TOPIC;
			public static final String ROOM = "/room/{" + PathVariables.ROOM_NAME + "}";
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
	}
	
	public static class StaticContent {
		public static final String TRIVIA_CLIENT = "index.html";
	}
	
}
