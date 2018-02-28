import { Input, Component, OnInit } from '@angular/core';
import { Room } from '../../domain/room/room';

@Component({
  selector: 'join-room-button',
  templateUrl: './room-button.component.html',
})
export class RoomButtonComponent {

  @Input() protected room: Room;
  @Input() protected selectedRoom: Room;
  protected activated: boolean;

  constructor() {
    this.activated = false;
   }

   toggleActivation(): void {
     this.activated = !this.activated;
   }

   get classes(): object {
     console.log(`Showable: ${this.showable}`);
     return {
       'activated': this.activated,
       'room-button': true,
       'hide': !this.showable
     };
   }

   get showable(): boolean {
     console.log(this.selectedRoom);
     return this.selectedRoom === undefined || this.selectedRoom === this.room;
   }

}
