import { RoomMessage } from '../chat-room-message/room-message';
import { User } from '../user/user';

export class Room {

  readonly name: string;
  readonly maxNumberOfUsers: number;
  readonly totalNumberOfUsers: number;
  readonly messages: Array<RoomMessage>;
  readonly users: Array<User>;

}
