import { Component, Input } from '@angular/core';
import { RoomMessage } from '../../domain/chat-room-message/room-message';
import { RoomService } from '../../services/room.service';

@Component({
  selector: 'chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {

  @Input()
  private roomName: string;

  @Input()
  private messages: Array<RoomMessage>;

  private newMessageBody: string;

  constructor(
    private roomService: RoomService
  ) { }

  sendMessage(): void {
    if(!this.newMessageBody) return;

    this.roomService.sendMessage({
      roomName: this.roomName,
      messageBody: this.newMessageBody
    });

    this.newMessageBody = "";
  }

}
