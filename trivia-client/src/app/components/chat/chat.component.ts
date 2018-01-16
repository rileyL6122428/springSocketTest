import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { RoomMessage } from '../../domain/chat-room-message/room-message';
import { RoomService } from '../../services/room.service';
import { Chat } from '../../domain/chat/chat';
import { Subscription } from 'rxjs/Subscription';

@Component({
  selector: 'chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, OnDestroy {

  @Input()
  private roomName: string;

  private chat: Chat;
  private newMessageBody: string;
  private subscriptions: Array<Subscription>

  constructor(
    private roomService: RoomService
  ) { }

  ngOnInit(): void {
    this.subscriptions = [
      this.roomService.fetchChat(this.roomName).subscribe((chat: Chat) => {
          this.chat = chat;
      }),

      this.roomService.getChatStompListener(this.roomName).subscribe((chat: Chat) => {
        this.chat = chat;
      })
    ];
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => {
      subscription.unsubscribe();
    });
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
