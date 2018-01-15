import { RoomMessage } from '../chat-room-message/room-message';
import { User } from '../user/user';
import { Chat } from '../chat/chat';

export class Room {

  constructor(params) {
    this.name = params['name'];
    this.maxNumberOfUsers = params['maxNumberOfUsers'];
    // this.messages = params['messages'];
    this.chat = params["chat"];
    this.users = params['users'];
  }

  readonly name: string;
  readonly maxNumberOfUsers: number;
  // readonly messages: Array<RoomMessage>;
  readonly chat: Chat;
  readonly users: Map<string, User>;

}
