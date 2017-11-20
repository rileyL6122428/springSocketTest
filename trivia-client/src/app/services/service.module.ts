import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { DomainFactoryModule } from '../domain/factory.module';
import { StompService, StompConfig } from '@stomp/ng2-stompjs';
import { STOMP_CONFIG } from '../stomp.config';
import { CookieService } from 'angular2-cookie/services/cookies.service'
import { UserService } from './user.service';
import { MatchmakingService } from './matchmaking.service';
import { RoomService } from './room.service';

@NgModule({
  imports: [
    HttpModule,
    DomainFactoryModule
  ],
  providers: [
    UserService,
    MatchmakingService,
    RoomService,
    { provide: StompConfig, useValue: STOMP_CONFIG },
    StompService,
    CookieService
  ]
})
export class ServicesModule {

}
