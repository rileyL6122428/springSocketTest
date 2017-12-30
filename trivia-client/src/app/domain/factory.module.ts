import { NgModule } from '@angular/core';
import { UserFactory } from './user/user.factory';
import { RoomMessageFactory } from './chat-room-message/room-message.factory';
import { GameMessageFactory } from './game-message/game-message.factory';
import { MatchmakingStatsFactory } from './matchmaking/matchmaking-stats.factory';
import { RoomFactory } from './room/room.factory';

@NgModule({
  providers: [
    UserFactory,
    RoomMessageFactory,
    GameMessageFactory,
    MatchmakingStatsFactory,
    RoomFactory
  ]
})
export class DomainFactoryModule { }
