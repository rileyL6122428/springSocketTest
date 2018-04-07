import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatchmakingHttpUtil } from './matchmaking.http';
import { MatchmakingWsUtil } from './matchmaking.ws';
import { MatchmakingStream } from './matchmaking.stream';

@NgModule({
  providers: [
    MatchmakingHttpUtil,
    MatchmakingWsUtil,
    MatchmakingStream
  ]
})
export class MatchmakingServiceModule { }
