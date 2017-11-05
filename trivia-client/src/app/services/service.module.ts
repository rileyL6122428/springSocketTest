import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';
import { DomainFactoryModule } from '../domain/factory.module';
import { UserService } from './user.service';

@NgModule({
  imports: [
    HttpModule,
    DomainFactoryModule
  ],
  providers: [ UserService ]
})
export class ServicesModule {

}
