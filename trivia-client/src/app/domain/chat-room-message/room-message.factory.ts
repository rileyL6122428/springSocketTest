import { Injectable } from '@angular/core';
import { RoomMessage } from './room-message';

@Injectable()
export class RoomMessageFactory {

  mapPOJO(messagePOJO: Object): RoomMessage {
    return messagePOJO as RoomMessage;
  }

}
