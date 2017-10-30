import { NgModule } from '@angular/core';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { StompServiceFacade } from './services/stomp.service.facade';

@NgModule({
  providers: [
    CookieService,
    StompServiceFacade
  ]
})
export class StompModule { }
