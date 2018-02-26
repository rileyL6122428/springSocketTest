import { RoomMessage } from '../chat-room-message/room-message';
import { User } from '../user/user';
import { Chat } from '../chat/chat';
import { Pokemon } from '../pokemon/pokemon';

export class Room {

  constructor(params) {
    this.name = params['name'];
    this.maxNumberOfUsers = params['maxNumberOfUsers'];
    this.chat = params['chat'];
    this.users = params['users'];
    this.mascot = params['mascot'];
  }
  readonly mascot: Pokemon;
  readonly name: string;
  readonly maxNumberOfUsers: number;
  readonly chat: Chat;
  readonly users: Map<string, User>;

}
