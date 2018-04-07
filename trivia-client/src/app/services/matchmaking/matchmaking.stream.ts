import { Injectable } from '@angular/core';
import { RoomStore } from '../../stores/room/room.store';
import { Subscription } from 'rxjs/Subscription';
import { StreamSubscription } from '../stream/stream-subscription';
import { MatchmakingHttpUtil } from './matchmaking.http';
import { MatchmakingWsUtil } from './matchmaking.ws';

@Injectable()
export class MatchmakingStream {

  constructor(
    private roomStore: RoomStore,
    private matchmakingHttp: MatchmakingHttpUtil,
    private matchmakingWs: MatchmakingWsUtil
  ) { }

  subscribe(onUpdate: (store: RoomStore) => void): StreamSubscription {
    return new StreamSubscription([
      this.placeStoreListener(onUpdate),
      this.fetchMatchmakingStats(),
      this.listenToMatchmaking()
    ]);
  }

  private placeStoreListener(listener: any): Subscription {
    return this.roomStore.placeListener(listener);
  }

  private fetchMatchmakingStats(): Subscription {
    return this.matchmakingHttp.fetchMatchmakingStats();
  }

  private listenToMatchmaking(): Subscription {
    return this.matchmakingWs.listenToMatchmaking();
  }

}
