package l2k.demo.multiple.chats.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import l2k.demo.multiple.chats.domain.User;
import l2k.demo.multiple.chats.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value="/user")
	public ResponseEntity<User> getUser(@CookieValue(value="TRIVIA_SESSION_COOKIE") String sessionId) {
		return new ResponseEntity<User>(userService.getUser(sessionId), HttpStatus.OK);
	}

}
