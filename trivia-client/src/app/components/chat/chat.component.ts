import { Component, Input } from '@angular/core';
import { RoomMessage } from '../../domain/chat-room-message/room-message';

@Component({
  selector: 'chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {

  @Input()
  private messages: Array<RoomMessage>;

}
