import { NgModule } from '@angular/core';

// import { STOMP_CONFIG } from './constants/stomp.config';
// import { StompService, StompConfig } from '@stomp/ng2-stompjs';
import { StompServiceFacade } from './services/stomp.service';

@NgModule({
  providers: [
    StompServiceFacade
  ]
})
export class StompModule { }
