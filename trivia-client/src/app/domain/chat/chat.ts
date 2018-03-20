import { ChatMessage } from './chat-message';

export class Chat {

  constructor(params) {
    this.messages = params['messages'];
    this.name = params['name'];
  }

  readonly messages: Array<ChatMessage>;
  readonly name: string;
}
