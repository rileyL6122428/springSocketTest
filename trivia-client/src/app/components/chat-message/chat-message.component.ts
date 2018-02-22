import { Component, Input } from '@angular/core';
import { ChatMessage } from '../../domain/chat/chat-message';

@Component({
  selector: 'chat-message',
  templateUrl: './chat-message.component.html',
  styleUrls: ['./chat-message.component.css']
})
export class ChatMessageComponent {

  @Input() private message: ChatMessage;

  constructor() { }

}
