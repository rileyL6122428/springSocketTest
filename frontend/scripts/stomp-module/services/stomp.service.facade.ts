import { Injectable } from '@angular/core';

import { STOMP_CONFIG } from '.././constants/stomp.config';
import { StompService, StompConfig } from '../../../vendor-workarounds/ngstomp-js/index';
import { Subscription } from 'rxjs';

@Injectable()
export class StompServiceFacade {

  private stompService: StompService;

  constructor() {
    this.stompService = new StompService(STOMP_CONFIG);
  }

  subscribe(path: string, callback: Function):Subscription {
      return this.stompService.subscribe(path).map((message) => {
        return JSON.parse(message.body);
      }).subscribe((messageBody: string) => {
        callback(messageBody);
      });
  }

  publish(path: string, message): void {
    this.stompService.publish(path, JSON.stringify(message), {});
  }
}
