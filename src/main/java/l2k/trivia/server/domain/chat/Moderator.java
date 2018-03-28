package l2k.trivia.server.domain.chat;

public class Moderator implements Sender {
	
	private String name;
	
	{
		name = "Professor Oak";
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
