import { Injectable } from '@angular/core';
import { Store } from '../base/base.store';
import { Room } from '../../domain/room/room';

@Injectable()
export class RoomStore extends Store<Room> {

  constructor() {
    super();
   }

   getByName(name: string): Room {
     return this.records.get(name);
   }

}
