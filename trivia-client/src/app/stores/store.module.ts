import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoomStore } from './room/room.store';

@NgModule({
  providers: [
    RoomStore
  ]
})
export class StoreModule { }
