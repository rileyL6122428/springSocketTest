import { Injectable } from '@angular/core';
import { Room } from './room';
import { UserFactory } from '../user/user.factory';
import { RoomMessageFactory } from '../chat-room-message/room-message.factory';
import { User } from '../user/user';
import { Chat } from '../chat/chat';
import { RoomMessage } from '../chat-room-message/room-message';
import { ChatFactory } from '../chat/chat.factory';
import { Pokemon } from '../pokemon/pokemon';

@Injectable()
export class RoomFactory {

  constructor(
    private userFactory: UserFactory,
    private roomMessageFactory: RoomMessageFactory,
    private chatFactory: ChatFactory
  ) { }

  fromPOJO(roomPOJO: Object): Room {
    const users: Map<string, User> = this.userFactory.mapPOJOMap(roomPOJO['users']);
    // const chat: Chat = this.chatFactory.fromPOJO(roomPOJO['chat']);
    const name: string = roomPOJO['name'];
    const maxNumberOfUsers: number = roomPOJO['userCapacity'];
    const mascot: Pokemon = new Pokemon(roomPOJO['mascot']['name']);
    const id: number = roomPOJO['name'];

    return new Room({ id, users, name, maxNumberOfUsers, mascot });
  }

  fromPOJOMapToList(roomsPOJOMap: Object): Array<Room> {
    const roomPOJOList: Array<object> = Object.values(roomsPOJOMap);

    return roomPOJOList
            .sort(byMatchmakingOrder)
            // .map((room: object) => roomsPOJOMap[room['name']])
            .map((roomPOJO: Object) => this.fromPOJO(roomPOJO));
  }

}

function byMatchmakingOrder(roomA, roomB): number {
  return roomA.matchmakingOrder - roomB.matchmakingOrder;
}
