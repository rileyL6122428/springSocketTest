package l2k.trivia.server.config;

public class Constants {
	
	
	public static class STOMP {
		
		public static class Endpoints {
			public static final String SESSION_CONNECTION = "/matchmaking";
			public static final String BROKER_TOPIC_PREFIX = "/topic";
			public static final String APP_MESSAGING_PREFIX = "/app";
			
			public static final String MATCHMAKING_SUBSCRIPTION = "/matchmaking";
		}
	}
	
	public static class HTTP {
		
		public static class Endpoints {
			public static final String MATCHMAKING_STATS = "/matchmaking/stats";
			public static final String MATCHMAKING_SELECTION = "/join-chat-room";
		}
		
	}
	
	public static class Cookies {
		public static final String SESSION_ID = "TRIVIA_SESSION_COOKIE";
	}
	
}
