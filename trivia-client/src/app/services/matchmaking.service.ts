import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/observable';
import { MatchmakingStats } from '../domain/matchmaking/matchmaking-stats';
import { Http } from '@angular/http';
import { MatchmakingStatsFactory } from '../domain/matchmaking/matchmaking-stats.factory';

@Injectable()
export class MatchmakingService {

  constructor(
    private http: Http,
    private matchmakingStatsFactory: MatchmakingStatsFactory
  ) { }

  getMatchmakingStats(): Observable<MatchmakingStats> {
    return this.http.get('/matchmaking/stats').map((response) => {
      if(response['status'] === 200)
        return this.matchmakingStatsFactory.createNewStats(response.json());
      else
        return null;
    });
  }

}
