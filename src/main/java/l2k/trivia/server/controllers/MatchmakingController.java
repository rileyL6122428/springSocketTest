package l2k.trivia.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import l2k.trivia.server.config.Constants.HTTP;
import l2k.trivia.server.controllers.wsmessages.MatchmakingStats;
import l2k.trivia.server.services.MatchmakingService;


@Controller
public class MatchmakingController {
	
	@Autowired private MatchmakingService matchmakingService;
	
	@GetMapping(value = HTTP.Endpoints.MATCHMAKING_STATS)
	public ResponseEntity<MatchmakingStats> getMatchmakingStats() {
		return new ResponseEntity<MatchmakingStats>(matchmakingService.getMatchmakingStats(), HttpStatus.OK);
	}

}
