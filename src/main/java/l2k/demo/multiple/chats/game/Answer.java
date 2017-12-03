package l2k.demo.multiple.chats.game;

public class Answer {
	
	private final String text;
	
	public Answer(String text) {
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
