export class RoomMessage {

  constructor(params?: Object) {
    this.body = params['body'];
    this.senderName = params['sender']['name'];
    this.timestamp = params['timestamp'];
  }

  readonly body: string;
  readonly senderName: string;
  readonly timestamp: Date;

}
