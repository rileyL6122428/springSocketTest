package l2k.demo.multiple.chats.domain;

public class Moderator implements Sender {
	
	private String name;
	
	{
		name = "MODERATOR";
	}

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
