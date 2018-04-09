import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { DomainFactoryModule } from '../domain/factory.module';
import { StompRService } from '@stomp/ng2-stompjs';
import { UserService } from './user/user.service';
import { StompInitializer } from './stomp/stomp.initializer';
import { ChatServiceModule } from './chat/chat.module';
import { MatchmakingServiceModule } from './matchmaking/matchmaking.module';
import { SessionServiceModule } from './session/session.module';

@NgModule({
  imports: [
    HttpModule,
    DomainFactoryModule,
    ChatServiceModule,
    MatchmakingServiceModule,
    SessionServiceModule
  ],
  providers: [
    UserService,
    StompRService,
    StompInitializer,
  ]
})
export class ServicesModule { }
