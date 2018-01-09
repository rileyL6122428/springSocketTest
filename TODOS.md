**TODOS**

Refactor the following:
	1. Matchmaking Controller *DONE*
	2. Base Controller e2e specs
	3. Refactor MatchmakingControllerSpec
	
Unit Specs for 
	1. Matchmaking Controller
	2. Matchmaking Controller Dependencies
	

Implement Trivia Game
	Frontend
	Backend

- [ ] decouple chat from game on backend

	
## GameManager Design

1 per room
Depends on Messaging Template
	**Messages Send**
		1. Game	Start
		2. Question Start
		3. Answer Receipt
		4. Question Close
		5. Game End
		6. Waiting for next Game
		
		
## Future Refactors
	
	* Put score on player object instead of using a score map
	
## Right Now
	
	Refactor everything up until now/ Add Tests
	Get Game loop working
	Refactor game loop/Add tests if necessary
	Add Styles
	
## Packages Refactored

	- [x] l2k.trivia.server.config
	- [ ] 
	