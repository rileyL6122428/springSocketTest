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
    const messages: Array<ChatMessage> = this.chatMessageFactory.mapPOJOList(chatPOJO['messages']);
    const name = chatPOJO['roomName'];
    return new Chat({ messages, name });
  }

}
