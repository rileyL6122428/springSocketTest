import { Component, Input, OnInit } from '@angular/core';
import { RoomMessage } from '../../domain/chat-room-message/room-message';
import { RoomService } from '../../services/room.service';
import { Chat } from '../../domain/chat/chat';
import { SubscribingComponent } from '../base/subscribing.component';

@Component({
  selector: 'chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent extends SubscribingComponent implements OnInit {

  @Input()
  private roomName: string;

  private chat: Chat;
  private newMessageBody: string;

  constructor(
    private roomService: RoomService
  ) {
    super();
  }

  ngOnInit(): void {
    this.addSubscriptions(
      this.roomService.fetchChat(this.roomName).subscribe((chat: Chat) => {
          this.chat = chat;
      }),

      this.roomService.getChatStompListener(this.roomName).subscribe((chat: Chat) => {
        this.chat = chat;
      })
    );
  }

  sendMessage(): void {
    if(!this.newMessageBody) return;

    this.roomService.sendMessage({
      roomName: this.roomName,
      messageBody: this.newMessageBody
    });

    this.newMessageBody = "";
  }

}
