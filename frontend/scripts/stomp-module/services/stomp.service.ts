import { Injectable } from '@angular/core';

import { STOMP_CONFIG } from '.././constants/stomp.config';
import { StompService, StompConfig } from '@stomp/ng2-stompjs';

@Injectable()
export class StompServiceFacade {

  constructor() {
    let stompService:StompService = new StompService(STOMP_CONFIG);
  }

}
