import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { DomainFactoryModule } from '../domain/factory.module';
import { StompService, StompConfig, StompRService } from '@stomp/ng2-stompjs';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { UserService } from './user/user.service';
import { MatchmakingService } from './matchmaking/matchmaking.service';
import { RoomService } from './room/room.service';
import { StompInitializer } from './stomp/stomp.initializer';
import { SessionService } from './session/session.service';
import { ChatStream } from './chat/chat.stream';
import { ChatHttpUtil } from './chat/chat.http';
import { ChatWSUtil } from './chat/chat.ws';

@NgModule({
  imports: [
    HttpModule,
    DomainFactoryModule
  ],
  providers: [
    UserService,
    MatchmakingService,
    RoomService,
    ChatStream,
    ChatHttpUtil,
    ChatWSUtil,
    StompRService,
    StompInitializer,
    CookieService,
    SessionService
  ]
})
export class ServicesModule { }
