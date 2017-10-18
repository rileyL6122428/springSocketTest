package l2k.demo.multiple.chats.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import l2k.demo.multiple.chats.domain.User;

@Controller
public class UserController {
	
	@GetMapping(value="/user")
	public ResponseEntity<User> getUser(@CookieValue(value="TRIVIA_SESSION_COOKIE") String sessionId) {
		System.out.println(sessionId);
		return null;
	}

}
