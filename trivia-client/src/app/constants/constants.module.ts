import { NgModule } from '@angular/core';
import { STOMP_CONFIG } from './stomp.config';

@NgModule({
  providers: [
    { provide: 'Constants.STOMP_CONFIG' , useValue: STOMP_CONFIG }
  ]
})
export class ConstantsModule { }
