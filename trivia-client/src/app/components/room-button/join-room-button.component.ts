import { Input, Component, OnInit } from '@angular/core';
import { Room } from '../../domain/room/room';

@Component({
  selector: 'join-room-button',
  templateUrl: './room-button.component.html',
})
export class RoomButtonComponent {

  @Input() protected room: Room;
  protected activated: boolean;

  constructor() {
    this.activated = false;
   }

   toggleActivation(): void {
     this.activated = !this.activated;
   }

   get classes(): object {
     return {
       'activated': this.activated,
       'room-button': true
     };
   }
}
