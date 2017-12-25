package l2k.trivia.server.constants;

public class ServerConstants {
	
	public static class Endpoints {
		public static final String GET_MATCHMAKING_STATS = "/matchmaking/stats";
		public static final String JOIN_ROOM = "/join-chat-room";
		public static final String SUBSCRIBE_TO_MATCHMAKING_STATS = "/matchmaking";
	}
	
	public static class Cookies {
		public static final String SESSION_ID = "TRIVIA_SESSION_COOKIE";
	}
	
}
