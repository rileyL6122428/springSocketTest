import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { RoomStore } from '../../stores/room/room.store';
import { Subscription } from 'rxjs/Subscription';
import { Observable } from 'rxjs/Observable';
import { MatchmakingStatsFactory } from '../../domain/matchmaking/matchmaking-stats.factory';
import { Room } from '../../domain/room/room';

@Injectable()
export class MatchmakingHttpUtil {

  constructor(
    private http: Http,
    private roomStore: RoomStore,
    private matchmakingStatsFactory: MatchmakingStatsFactory
  ) { }

  fetchMatchmakingStats(): Subscription {
    return this.http.get('/matchmaking/stats').subscribe((response) => {
      if (response['status'] === 200) {
        const stats = this.matchmakingStatsFactory.fromPOJO(response.json());
        this.roomStore.depositList(stats.rooms);
      }
    });
  }

  joinRoom(room: Room): Observable<boolean> {
    return this.http.post(`/room/${room.name}/join`, null)
      .map( (response) => response[`status`] === 200 );
  }

  leaveRoom(room: Room): Observable<boolean> {
    return this.http.post(`/room/${room.name}/leave`, null)
      .map( (response) =>  response[`status`] === 200 );
  }

}
