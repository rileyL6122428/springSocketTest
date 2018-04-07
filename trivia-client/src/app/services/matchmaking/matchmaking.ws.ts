import { Injectable } from '@angular/core';
import { RoomStore } from '../../stores/room/room.store';
import { StompRService } from '@stomp/ng2-stompjs';
import { MatchmakingStatsFactory } from '../../domain/matchmaking/matchmaking-stats.factory';
import { Subscription } from 'rxjs/Subscription';
import { Message } from '@stomp/ng2-stompjs/node_modules/@stomp/stompjs';
import { SessionService } from '../session/session.service';

@Injectable()
export class MatchmakingWsUtil {

  constructor(
    private matchmakingFactory: MatchmakingStatsFactory,
    private stompService: StompRService,
    private sessionService: SessionService,
    private roomStore: RoomStore,
  ) {}

  listenToMatchmaking(): Subscription {
    return this.stompService.subscribe('/topic/matchmaking', this.sessionHeader())
      .map((message: Message) => JSON.parse(message.body))
      .map ((messageBody) => this.matchmakingFactory.fromPOJO(messageBody))
      .subscribe((stats) => this.roomStore.depositList(stats.rooms));
  }

  private sessionHeader() {
    return { SESSION_ID: this.sessionService.sessionToken };
  }

}
