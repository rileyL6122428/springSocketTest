import { Injectable } from '@angular/core';
import { Room } from './room';
import { UserFactory } from '../user/user.factory';
import { RoomMessageFactory } from '../chat-room-message/room-message.factory';

@Injectable()
export class RoomFactory {

  constructor(
    private userFactory: UserFactory,
    private roomMessageFactory: RoomMessageFactory
  ) {
  }

  fromPOJO(roomPOJO: Object): Room {
    return roomPOJO as Room;
  }

  fromPOJOMapToList(roomPOJOs: Map<string, Object>): Array<Room> {
    let roomNames: Array<string> = Array.from(roomPOJOs.keys());

    return roomNames
            .sort()
            .map((roomName:string) => roomPOJOs.get(roomName))
            .map((roomPOJO: Object) => this.fromPOJO(roomPOJO));
  }

}
