import { NgModule } from '@angular/core';

import { StompServiceFacade } from './services/stomp.service.facade';

@NgModule({
  providers: [
    StompServiceFacade
  ]
})
export class StompModule { }
