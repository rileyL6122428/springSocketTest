import { TestBed, inject } from '@angular/core/testing';
import { MatchmakingStats } from './matchmaking-stats';
import { MatchmakingStatsFactory } from './matchmaking-stats.factory';
import { Room } from '../room/room';
import { DomainFactoryModule } from '../factory.module';

describe('matchmaking-stats', () => {

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ DomainFactoryModule ]
    });
  });

  describe('#createNewStats', () => {
    xit("calculates the correct number of users in the matchmaking process",
      inject([MatchmakingStatsFactory], (statsFactory: MatchmakingStatsFactory) => {
        
      }
    ));

    xit("orders the list of rooms",
      inject([MatchmakingStatsFactory], (statsFactory: MatchmakingStatsFactory) => {

      }
    ));
  });

});
