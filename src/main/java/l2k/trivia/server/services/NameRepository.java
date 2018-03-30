package l2k.trivia.server.services;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import l2k.trivia.server.config.Constants.BeanDefinitions;

@Service
public class NameRepository {
	
	private boolean takeFromFront;
	
	@Autowired
	@Qualifier(BeanDefinitions.USER_NAMES)
	private LinkedList<String> availableNames;
	
	public String getName() {
		return (availableNames.isEmpty()) ? null : nameAtEndOfList();
	}
	
	private String nameAtEndOfList() {
		takeFromFront = !takeFromFront;
		return (takeFromFront) ? availableNames.removeFirst() : availableNames.removeLast();
	}
	
	public void addName(String name) {
		availableNames.addLast(name);
	}
	
}
