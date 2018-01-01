package l2k.trivia.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Answer {
	
	private final String text;
	
	@JsonCreator
	public Answer(@JsonProperty("text") String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	@Override
	public boolean equals(Object object) {
		if(!(object instanceof Answer)) return false;
		
		Answer otherAnswer = (Answer) object;
		return otherAnswer != null && this.text.equals(otherAnswer.getText());
	}
	
	@Override
	public int hashCode() {
		return text.hashCode();
	}
	
	@Override
	public String toString() {
		return "Answer: " + text;
	}
	
}
