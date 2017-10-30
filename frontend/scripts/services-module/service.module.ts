import { NgModule } from '@angular/core';
import { StompModule } from '../stomp-module/stomp.module';
import { UserService } from './user.service';
import { RoomService } from './room.service';
import { MatchmakingService } from './matchmaking.service';

@NgModule({
  imports: [ StompModule ],
  providers: [
    UserService,
    RoomService,
    MatchmakingService
  ]
})
export class TriviaServiceModule { }
