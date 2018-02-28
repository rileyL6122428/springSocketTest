import { Input } from '@angular/core';
import { Room } from '../../domain/room/room';

export class RoomButtonBase {

  @Input() protected room: Room;
  protected activated: boolean;

  constructor(params: { activated: boolean }) {
    this.activated = params.activated;
  }

}
