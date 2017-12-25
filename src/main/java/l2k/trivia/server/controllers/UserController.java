package l2k.trivia.server.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import l2k.trivia.server.controllers.response.TriviaApiError;
import l2k.trivia.server.domain.User;
import l2k.trivia.server.services.CookieUtil;
import l2k.trivia.server.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CookieUtil cookieUtil;
	
	@GetMapping(value="/user")
	public ResponseEntity<?> getUser(@CookieValue(value="TRIVIA_SESSION_COOKIE") String triviaSessionCookie) {
		UUID sessionId = cookieUtil.cookieValueToUUID(triviaSessionCookie);
		User user = userService.getUser(sessionId);
		
		if(user == null) {
			return new ResponseEntity<TriviaApiError>(new TriviaApiError("User not found."), HttpStatus.NOT_FOUND);
		} else {			
			return new ResponseEntity<User>(userService.getUser(sessionId), HttpStatus.OK);
		}
	}

}
