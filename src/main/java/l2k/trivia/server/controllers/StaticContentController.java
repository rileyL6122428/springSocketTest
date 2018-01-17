package l2k.trivia.server.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import l2k.trivia.server.config.Constants.HTTP;
import static l2k.trivia.server.config.Constants.StaticContent.TRIVIA_CLIENT;

@Controller
public class StaticContentController {

	@GetMapping(value = HTTP.Endpoints.APP_ROOT)
	public ModelAndView getClient() {
		return new ModelAndView(TRIVIA_CLIENT);
	}
	
}

