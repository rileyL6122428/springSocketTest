package l2k.trivia.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import l2k.trivia.server.config.Constants.HTTP;
import l2k.trivia.server.config.Constants.STOMP;
import l2k.trivia.server.controllers.wsmessages.MatchmakingStats;
import l2k.trivia.server.dispatcher.MatchmakingDispatcher;
import l2k.trivia.server.services.MatchmakingService;


@Controller
public class MatchmakingController {
	
	private MatchmakingService matchmakingService;
	private MatchmakingDispatcher matchmakingDispatcher;
	
	@Autowired
	public MatchmakingController(MatchmakingService matchmakingService, MatchmakingDispatcher dispatcher) {
		this.matchmakingService = matchmakingService;
		this.matchmakingDispatcher = dispatcher;
	}
	
	@SubscribeMapping(STOMP.Endpoints.MATCHMAKING_SUBSCRIPTION)
	public void subscribeToMatchmaking() {
		matchmakingDispatcher.dispatchStats(matchmakingService.getMatchmakingStats());
	}
	
	@GetMapping(value = HTTP.Endpoints.MATCHMAKING_STATS)
	public ResponseEntity<MatchmakingStats> getMatchmakingStats() {
		return new ResponseEntity<MatchmakingStats>(matchmakingService.getMatchmakingStats(), HttpStatus.OK);
	}

}
