import { Injectable, Inject } from '@angular/core';
import { StompRService } from '@stomp/ng2-stompjs';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { StompConfig } from '@stomp/ng2-stompjs';
import { environment } from '../../../environments/environment';
import { SessionService } from '../session/session.service';

@Injectable()
export class StompInitializer {

  constructor(
    private stompService: StompRService,
    private cookieService: CookieService,
    private sessionService: SessionService,
    @Inject('Constants.STOMP_CONFIG') private STOMP_CONFIG
  ) { }

  setupStompService(): void {
    this.stompService.config = { ...this.STOMP_CONFIG, ...this.sessionHeaders() };
    this.stompService.initAndConnect();
  }

  private sessionHeaders() {
    return {
      headers: { SESSION_ID: this.sessionService.sessionToken }
    };
  }

}
