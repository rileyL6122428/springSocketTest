import { Injectable } from '@angular/core';
import { RoomMessage } from './room-message';

@Injectable()
export class RoomMessageFactory {

  mapPOJO(messagePOJO: Object): RoomMessage {
    messagePOJO['timestamp'] = new Date(messagePOJO['timestamp']);
    return new RoomMessage(messagePOJO);
  }

  mapPOJOList(messagePOJOs: Array<Object>): Array<RoomMessage> {
    return messagePOJOs.map((messagePOJO) => {
      return this.mapPOJO(messagePOJO);
    });
  }

}
