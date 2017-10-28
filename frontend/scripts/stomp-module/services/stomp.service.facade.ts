import { Injectable } from '@angular/core';
import { STOMP_CONFIG } from '.././constants/stomp.config';
import { StompService, StompConfig, StompHeaders } from '../../../vendor-workarounds/ngstomp-js/index';
import { Subscription } from 'rxjs';
import { CookieService } from 'angular2-cookie/services/cookies.service'

@Injectable()
export class StompServiceFacade {

  private stompService: StompService;

  constructor(
    private cookieService: CookieService
  ) {
    this.stompService = new StompService(STOMP_CONFIG);
  }

  subscribe(path: string, callback: Function):Subscription {
    return this.stompService.subscribe(path, this.getHeaders()).map((message) => {
      return JSON.parse(message.body);
    }).subscribe((messageBody: string) => {
      callback(messageBody);
    });
  }

  publish(path: string, message): void {
    this.stompService.publish(path, JSON.stringify(message), this.getHeaders());
  }

  private getHeaders(): StompHeaders {
    let sessionId = this.cookieService.get("TRIVIA_SESSION_COOKIE");
    return { testHeader: sessionId };
  }
}
