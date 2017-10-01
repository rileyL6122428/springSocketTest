import { Injectable } from '@angular/core';

import { STOMP_CONFIG } from '.././constants/stomp.config';
import { StompService, StompConfig } from '../../../vendor-workarounds/ngstomp-js/index';

@Injectable()
export class StompServiceFacade {

  constructor() {
    let stompService:StompService = new StompService(STOMP_CONFIG);
  }

}
