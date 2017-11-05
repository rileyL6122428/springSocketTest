import { NgModule } from '@angular/core';
import { UserFactory } from './user/User.factory';

@NgModule({
  providers: [ UserFactory ]
})
export class DomainFactoryModule { }
