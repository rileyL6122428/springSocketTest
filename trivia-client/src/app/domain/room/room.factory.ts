import { Injectable } from '@angular/core';
import { Room } from './room';
import { UserFactory } from '../user/user.factory';
import { RoomMessageFactory } from '../chat-room-message/room-message.factory';
import { User } from '../user/user';
import { RoomMessage } from '../chat-room-message/room-message';

@Injectable()
export class RoomFactory {

  constructor(
    private userFactory: UserFactory,
    private roomMessageFactory: RoomMessageFactory
  ) {
  }

  fromPOJO(roomPOJO: Object): Room {
    let users: Map<string, User> = this.userFactory.mapPOJOMap(roomPOJO['users']);
    let messages: Array<RoomMessage> = this.roomMessageFactory.mapPOJOList(roomPOJO['messages']);
    let name: string = roomPOJO["name"];
    let maxNumberOfUsers: number = roomPOJO['maxNumberOfUsers'];

    return new Room({ users, messages, name, maxNumberOfUsers });
  }

  fromPOJOMapToList(roomPOJOs: Object): Array<Room> {
    let roomNames: Array<string> = Object.keys(roomPOJOs);

    return roomNames
            .sort()
            .map((roomName:string) => roomPOJOs[roomName])
            .map((roomPOJO: Object) => this.fromPOJO(roomPOJO));
  }

}
