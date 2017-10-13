export class ChatRoomMessage {

  static fromPOJO(params: Object): ChatRoomMessage {
    let chatRoomMessage: ChatRoomMessage = new ChatRoomMessage();

    chatRoomMessage.body = params['body'];
    chatRoomMessage.timestamp = new Date(params['timestamp']);
    chatRoomMessage.senderName = params['sender']['name'];

    return chatRoomMessage;
  }

  private body: string;
  private senderName: string;
  private timestamp: Date;

  public getBody(): string {
    return this.body;
  }

  public getTimestamp(): Date {
    return this.timestamp;
  }

  public getSenderName(): string {
    return this.senderName;
  }

}
