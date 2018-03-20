import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoomStore } from './room/room.store';
import { ChatStore } from './chat/chat.store';

@NgModule({
  providers: [
    RoomStore, ChatStore
  ]
})
export class StoreModule { }
