package l2k.trivia.game;

public class Question {

	private final String text;

	public Question(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
	@Override
	public boolean equals(Object object) {
		if(!(object instanceof Question)) return false;
		
		Question otherQuestion = (Question) object;
		return otherQuestion != null && this.text.equals(otherQuestion.getText());
	}
	
	@Override
	public int hashCode() {
		return text.hashCode();
	}
	
	@Override
	public String toString() {
		return "Question: " + text;
	}

}
