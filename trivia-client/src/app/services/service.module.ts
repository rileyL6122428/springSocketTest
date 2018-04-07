import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { DomainFactoryModule } from '../domain/factory.module';
import { StompService, StompConfig, StompRService } from '@stomp/ng2-stompjs';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { UserService } from './user/user.service';
import { StompInitializer } from './stomp/stomp.initializer';
import { SessionService } from './session/session.service';
import { ChatServiceModule } from './chat/chat.module';
import { MatchmakingServiceModule } from './matchmaking/matchmaking.module';

@NgModule({
  imports: [
    HttpModule,
    DomainFactoryModule,
    ChatServiceModule,
    MatchmakingServiceModule
  ],
  providers: [
    UserService,
    StompRService,
    StompInitializer,
    CookieService,
    SessionService
  ]
})
export class ServicesModule { }
