package l2k.demo.multiple.chats.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswerMap {
	
	private Map<String, String> answers;

	public AnswerMap(List<String> shuffledAnswers) {
		answers = new HashMap<String, String>();
		
		answers.put("a", shuffledAnswers.get(0));
		answers.put("b", shuffledAnswers.get(1));
		answers.put("c", shuffledAnswers.get(2));
		answers.put("d", shuffledAnswers.get(3));
	}

	public String getAnswer(String key) {
		if(!answers.containsKey(key)) throw new RuntimeException("KEY NOT FOUND IN ANSWER MAP");
		return answers.get(key);
	}

}
