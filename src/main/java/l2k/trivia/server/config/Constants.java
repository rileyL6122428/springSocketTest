package l2k.trivia.server.config;

public class Constants {
	
	
	public static class STOMP {
		
		public static class PathPrefixes {			
			public static final String BROKER_TOPIC = "/topic";
			public static final String APP_MESSAGING = "/app";
			public static final String BROKER_SUBSCRIPTION_LISTENING = BROKER_TOPIC;
		}
		
		public static class Endpoints {
			public static final String SESSION_CONNECTION = "/trivia-sockets";
			public static final String MATCHMAKING_SUBSCRIPTION = "/matchmaking";
		}
	}
	
	public static class HTTP {
		
		public static class Endpoints {
			public static final String APP_ROOT = "/";
			public static final String MATCHMAKING_STATS = "/matchmaking/stats";
			public static final String MATCHMAKING_SELECTION = "/join-chat-room";
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
	
}
