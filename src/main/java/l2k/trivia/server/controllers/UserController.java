package l2k.trivia.server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import l2k.trivia.server.config.Constants.HTTP;
import l2k.trivia.server.domain.User;

@Controller
public class UserController {
	
	@GetMapping(value=HTTP.Endpoints.USER)
	public ResponseEntity<?> getUser(
			@RequestAttribute(HTTP.RequestAttribute.USER) User user
		) {
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
