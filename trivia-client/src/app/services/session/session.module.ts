import { NgModule } from '@angular/core';
import { SessionHttpUtil } from './session.http';
import { SessionService } from './session.service';
import { CookieService } from 'angular2-cookie/services/cookies.service';

@NgModule({
  providers: [
    SessionHttpUtil,
    SessionService,
    CookieService
  ]
})
export class SessionServiceModule { }
