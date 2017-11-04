import { NgModule } from '@angular/core';
import { UserDomainFactory } from './user/User.factory';

@NgModule({
  providers: [ UserDomainFactory ]
})
export class DomainFactoryModule { }
