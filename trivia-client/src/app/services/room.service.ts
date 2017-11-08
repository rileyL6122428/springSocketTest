import { Injectable } from '@angular/core';
import { RoomFactory } from '../domain/room/room.factory';

@Injectable()
export class RoomService {

  constructor(
    private roomFactory: RoomFactory
  ) { }

}
