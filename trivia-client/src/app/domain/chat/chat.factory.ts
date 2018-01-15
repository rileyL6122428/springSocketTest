import { Injectable } from '@angular/core';
import { ChatMessageFactory } from './chat-message.factory';
import { ChatMessage } from './chat-message';
import { Chat } from './chat';

@Injectable()
export class ChatFactory {

  constructor(
    private chatMessageFactory: ChatMessageFactory
  ) { }

  fromPOJO(chatPOJO: Object): Chat {
    let messages: Array<ChatMessage> = this.chatMessageFactory.mapPOJOList(chatPOJO['messages']);
    return new Chat({ messages });
  }

}
