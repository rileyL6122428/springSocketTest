import { Injectable } from '@angular/core';
import { StompRService } from '@stomp/ng2-stompjs';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { StompConfig } from '@stomp/ng2-stompjs';
import { environment } from '../../../environments/environment';

@Injectable()
export class StompInitializer {

  constructor(
    private stompService: StompRService,
    private cookieService: CookieService
  ) { }

  setupStompService(): void {
    this.stompService.config = this.getStompConfig();
    this.stompService.initAndConnect();
  }

  private getStompConfig(): StompConfig {
    return {
      // url: 'ws://localhost:8090/trivia-sockets/websocket',
      // url: 'ws://192.168.0.7:8090/trivia-sockets/websocket',
      // url: 'wss://poke-trivia.herokuapp.com/trivia-sockets/websocket',
      url: environment.webSockets.ADDRESS,

      headers: {
        SESSION_ID: this.cookieService.get('TRIVIA_SESSION_COOKIE')
      },

      heartbeat_in: 0,
      heartbeat_out: 20000,
      reconnect_delay: 5000,
      debug: true
    };

  }

}
