import { Injectable } from '@angular/core';
import { ChatMessage } from './chat-message';

@Injectable()
export class ChatMessageFactory {

  mapPOJO(messagePOJO: Object): ChatMessage {
    messagePOJO['timestamp'] = new Date(messagePOJO['timestamp']);
    return new ChatMessage(messagePOJO);
  }

  mapPOJOList(messagePOJOs: Array<Object>): Array<ChatMessage> {
    return messagePOJOs.map((messagePOJO) => {
      return this.mapPOJO(messagePOJO);
    });
  }

}
