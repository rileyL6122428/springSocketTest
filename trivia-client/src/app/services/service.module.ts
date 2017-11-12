import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { DomainFactoryModule } from '../domain/factory.module';
import { UserService } from './user.service';
import { MatchmakingService } from './matchmaking.service';

@NgModule({
  imports: [
    HttpModule,
    DomainFactoryModule
  ],
  providers: [ UserService, MatchmakingService ]
})
export class ServicesModule {

}
