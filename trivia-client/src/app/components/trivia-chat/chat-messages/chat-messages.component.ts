import { Component, Input } from '@angular/core';
import { ChatMessage } from '../../../domain/chat/chat-message';

@Component({
  selector: 'chat-messages',
  templateUrl: './chat-messages.component.html',
  styleUrls: ['./chat-messages.component.scss']
})
export class ChatMessagesComponent {

  @Input() private messages: Array<ChatMessage>;

}
