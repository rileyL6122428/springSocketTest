package l2k.trivia.server.controllers.response;

public class TriviaApiError {
	
	private String message;
	
	public TriviaApiError(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
