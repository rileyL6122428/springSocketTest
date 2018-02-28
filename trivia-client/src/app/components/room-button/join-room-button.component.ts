import { Component, OnInit } from '@angular/core';
import { RoomButtonBase } from './room-button.component';

@Component({
  selector: 'join-room-button',
  templateUrl: './room-button.component.html',
})
export class JoinRoomButtonComponent extends RoomButtonBase {

  constructor() {
    super({ activated: false });
   }

}
