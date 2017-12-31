import { NgModule } from '@angular/core';
import { UserFactory } from './user/user.factory';
import { RoomMessageFactory } from './chat-room-message/room-message.factory';
import { PlayerFactory } from './player/player.factory';
import { GameFactory } from './game/game.factory';
import { MatchmakingStatsFactory } from './matchmaking/matchmaking-stats.factory';
import { RoomFactory } from './room/room.factory';
import { QuestionFactory } from './game/question.factory';
import { AnswerFactory } from './game/answer.factory';

@NgModule({
  providers: [
    UserFactory,
    RoomMessageFactory,
    PlayerFactory,
    GameFactory,
    MatchmakingStatsFactory,
    RoomFactory,
    QuestionFactory,
    AnswerFactory
  ]
})
export class DomainFactoryModule { }
