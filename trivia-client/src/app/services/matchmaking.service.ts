import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/observable';
import { MatchmakingStats } from '../domain/matchmaking/matchmaking-stats';
import { Http } from '@angular/http';
import { MatchmakingStatsFactory } from '../domain/matchmaking/matchmaking-stats.factory';
import { StompService } from '@stomp/ng2-stompjs';
import { Message } from '@stomp/stompjs';
import { map } from "rxjs/operator/map";

@Injectable()
export class MatchmakingService {

  constructor(
    private http: Http,
    private matchmakingStatsFactory: MatchmakingStatsFactory,
    private stompService: StompService
  ) { }

  getMatchmakingStats(): Observable<MatchmakingStats> {
    return this.http.get('/matchmaking/stats').map((response) => {
      if(response['status'] === 200)
        return this.matchmakingStatsFactory.createNewStats(response.json());
      else
        return null;
    });
  }

  subscribeToMatchmaking(): Observable<MatchmakingStats> {

    return this.stompService.subscribe('queue/name')
      .map((message: Message) => {
        return null;
      });
  }

}
