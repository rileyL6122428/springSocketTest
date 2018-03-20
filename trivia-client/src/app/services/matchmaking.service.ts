import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { MatchmakingStats } from '../domain/matchmaking/matchmaking-stats';
import { Http } from '@angular/http';
import { MatchmakingStatsFactory } from '../domain/matchmaking/matchmaking-stats.factory';
import { StompRService } from '@stomp/ng2-stompjs';
import { Message } from '@stomp/stompjs';
import 'rxjs/operator/map';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { RoomStore } from '../stores/room/room.store';
import { Subscription } from 'rxjs/Subscription';
import { Room } from '../domain/room/room';

@Injectable()
export class MatchmakingService {



  constructor(
    private http: Http,
    private matchmakingStatsFactory: MatchmakingStatsFactory,
    private stompService: StompRService,
    private cookieService: CookieService,
    private roomStore: RoomStore
  ) { }

  stream(onUpdate: (store: RoomStore) => void): { unsubscribe: () => void } {
    onUpdate(this.roomStore);

    const subs = [
      this.placeStoreListener(onUpdate),
      this.fetchMakingStats(),
      this.listenForMatchmakingStats()
    ];

    return {
      unsubscribe() { subs.forEach( (sub) => sub.unsubscribe() ); }
    };
  }

  private placeStoreListener(listener: any): Subscription {
    return this.roomStore.updates.subscribe(() => {
      listener(this.roomStore);
    });
  }

  private fetchMakingStats(): Subscription {
    return this.http.get('/matchmaking/stats').subscribe((response) => {
      if (response['status'] === 200) {
        const stats = this.matchmakingStatsFactory.createNewStats(response.json());
        this.roomStore.depositList(stats.rooms);
      }
    });
  }

  private listenForMatchmakingStats(): Subscription {
    const headers = { SESSION_ID: this.cookieService.get('TRIVIA_SESSION_COOKIE') };

    return this.stompService.subscribe('/topic/matchmaking', headers)
      .map((message: Message) => {
        const statsPayload = JSON.parse(message.body);
        return this.matchmakingStatsFactory.createNewStats(statsPayload);
      })
      .subscribe((stats) => {
        this.roomStore.depositList(stats.rooms);
      });
  }

  joinRoom(room: Room): Observable<boolean> {
    return this.http.post(`/room/${room.name}/join`, null).map((response) => {
      return response[`status`] === 200;
    });
  }

}
