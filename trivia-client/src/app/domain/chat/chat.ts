import { ChatMessage } from './chat-message';

export class Chat {

  constructor(params) {
    this.messages = params["messages"];
  }

  readonly messages: Array<ChatMessage>;

}
