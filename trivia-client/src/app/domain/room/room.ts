import { RoomMessage } from '../chat-room-message/room-message';
import { User } from '../user/user';

export class Room {

  constructor(params) {
    this.name = params['name'];
    this.maxNumberOfUsers = params['maxNumberOfUsers'];
    this.messages = params['messages'];
    this.users = params['users'];
  }

  readonly name: string;
  readonly maxNumberOfUsers: number;
  readonly messages: Array<RoomMessage>;
  readonly users: Map<string, User>;

}
