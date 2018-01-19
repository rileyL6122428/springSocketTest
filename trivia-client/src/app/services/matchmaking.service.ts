import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { MatchmakingStats } from '../domain/matchmaking/matchmaking-stats';
import { Http } from '@angular/http';
import { MatchmakingStatsFactory } from '../domain/matchmaking/matchmaking-stats.factory';
import { StompRService } from '@stomp/ng2-stompjs';
import { Message } from '@stomp/stompjs';
import "rxjs/operator/map";
import { CookieService } from 'angular2-cookie/services/cookies.service'

@Injectable()
export class MatchmakingService {

  constructor(
    private http: Http,
    private matchmakingStatsFactory: MatchmakingStatsFactory,
    private stompService: StompRService,
    private cookieService: CookieService
  ) { }

  getMatchmakingStats(): Observable<MatchmakingStats> {
    return this.http.get('/matchmaking/stats').map((response) => {
      if(response['status'] === 200)
        return this.matchmakingStatsFactory.createNewStats(response.json());
      else
        return null;
    });
  }

  joinRoom(roomName: string): Observable<boolean> {
    debugger
    return this.http.post(`/room/${roomName}/join`, null).map((response) => {
      return response[`status`] === 200;
    });
  }

  subscribeToMatchmaking(): Observable<MatchmakingStats> {
    let headers = { testHeader: this.cookieService.get("TRIVIA_SESSION_COOKIE") };

    return this.stompService.subscribe('/topic/matchmaking', headers)
      .map((message: Message) => {
        let statsPayload = JSON.parse(message.body);
        return this.matchmakingStatsFactory.createNewStats(statsPayload);
      });
  }

}
