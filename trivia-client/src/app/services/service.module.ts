import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { DomainFactoryModule } from '../domain/factory.module';
import { UserService } from './user.service';
import { MatchmakingService } from './matchmaking.service';
import { StompService, StompConfig } from '@stomp/ng2-stompjs';
import { STOMP_CONFIG } from '../stomp.config';

@NgModule({
  imports: [
    HttpModule,
    DomainFactoryModule
  ],
  providers: [
    UserService,
    MatchmakingService,
    { provide: StompConfig, useValue: STOMP_CONFIG },
    StompService
  ]
})
export class ServicesModule {

}
