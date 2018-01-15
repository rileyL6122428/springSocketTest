import { Injectable } from '@angular/core';
import { Room } from './room';
import { UserFactory } from '../user/user.factory';
import { RoomMessageFactory } from '../chat-room-message/room-message.factory';
import { User } from '../user/user';
import { Chat } from '../chat/chat';
import { RoomMessage } from '../chat-room-message/room-message';
import { ChatFactory } from '../chat/chat.factory';

@Injectable()
export class RoomFactory {

  constructor(
    private userFactory: UserFactory,
    private roomMessageFactory: RoomMessageFactory,
    private chatFactory: ChatFactory
  ) {
  }

  fromPOJO(roomPOJO: Object): Room {
    let users: Map<string, User> = this.userFactory.mapPOJOMap(roomPOJO['users']);
    // let messages: Array<RoomMessage> = this.roomMessageFactory.mapPOJOList(roomPOJO['messages']);
    let chat: Chat = this.chatFactory.fromPOJO(roomPOJO['chat']);
    let name: string = roomPOJO["name"];
    let maxNumberOfUsers: number = roomPOJO['userCapacity'];

    return new Room({ users, chat, name, maxNumberOfUsers });
  }

  fromPOJOMapToList(roomPOJOs: Object): Array<Room> {
    let roomNames: Array<string> = Object.keys(roomPOJOs);

    return roomNames
            .sort()
            .map((roomName:string) => roomPOJOs[roomName])
            .map((roomPOJO: Object) => this.fromPOJO(roomPOJO));
  }

}
