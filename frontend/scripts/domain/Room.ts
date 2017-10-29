import { ChatRoomMessage } from './ChatRoomMessage';
import { User } from './User';

export class Room {

  public static fromPOJOMapToList(roomMap: Map<string, Room>): Array<Room> {
    let rooms: Array<Room> = new Array<Room>();

    for(let roomName in roomMap) {
      let roomPOJO = roomMap[roomName];
      let room: Room = Room.fromPOJO(roomPOJO);
      rooms.push(room);
    }

    return rooms;
  }

  public static fromPOJO(pojo: Object) {
    let room: Room = new Room();

    room.name = pojo['name'];
    room.maxNumberOfUsers = pojo['maxNumberOfUsers'];
    room.totalNumberOfUsers = pojo['totalNumberOfUsers'];
    room.messages = Room.messagesFromPOJOList(pojo['messages']);
    room.users = Room.usersFromPOJOList(pojo['users']);

    return room;
  }

  private static messagesFromPOJOList(messagePOJOs: Array<Object>): Array<ChatRoomMessage> {
    let messages = new Array<ChatRoomMessage>();

    messagePOJOs.forEach((messagePOJO: Object) => {
      messages.push(ChatRoomMessage.fromPOJO(messagePOJO));
    });

    return messages;
  }

  private static usersFromPOJOList(usersMap: Object): Array<User> {
    let users = new Array<User>();

    for(let name in usersMap) {
      let userPOJO: Object = usersMap[name];
      users.push(User.fromPOJO(userPOJO));
    }

    return users;
  }

  private name: string;
  private maxNumberOfUsers: number;
  private totalNumberOfUsers: number;
  private messages: Array<ChatRoomMessage>;
  private users: Array<User>;

  public setName(name: string): void {
    this.name = name;
  }

  public getName(): string {
    return this.name;
  }

  public getMaxNumberOfUsers(): number {
    return this.maxNumberOfUsers;
  }

  public getTotalNumberOfUsers(): number {
    return this.totalNumberOfUsers;
  }

  public getMessages(): Array<ChatRoomMessage> {
    return this.messages;
  }

  public getUsers():  Array<User> {
    return this.users;
  }

}
