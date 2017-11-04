import { NgModule } from '@angular/core';
import { UserFactory } from './User/User.factory';

@NgModule({
  providers: [ UserFactory ]
})
export class DomainFactoryModule { }
