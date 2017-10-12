import { ChatRoomMessage } from './ChatRoomMessage';

export class Room {

  public static fromPOJO(pojo: Object) {
    let room: Room = new Room();

    room.name = pojo['name'];
    room.maxNumberOfUsers = pojo['maxNumberOfUsers'];
    room.totalNumberOfUsers = pojo['totalNumberOfUsers'];
    room.messages = Room.messagesFromPOJOList(pojo['messages']);

    return room;
  }

  private static messagesFromPOJOList(messagePOJOs: Array<Object>): Array<ChatRoomMessage> {
    let messages = new Array<ChatRoomMessage>();

    messagePOJOs.forEach((messagePOJO: Object) => {
      messages.push(ChatRoomMessage.fromPOJO(messagePOJO));
    });

    return messages;
  }

  private name: string;
  private maxNumberOfUsers: number;
  private totalNumberOfUsers: number;
  private messages: Array<ChatRoomMessage>;


  public getName(): string {
    return this.name;
  }

  public getMaxNumberOfUsers(): number {
    return this.maxNumberOfUsers;
  }

  public getTotalNumberOfUsers(): number {
    return this.totalNumberOfUsers;
  }

}
