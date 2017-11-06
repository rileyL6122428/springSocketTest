import { NgModule } from '@angular/core';
import { UserFactory } from './user/user.factory';
import { RoomMessageFactory } from './chat-room-message/room-message.factory';

@NgModule({
  providers: [
    UserFactory,
    RoomMessageFactory
  ]
})
export class DomainFactoryModule { }
