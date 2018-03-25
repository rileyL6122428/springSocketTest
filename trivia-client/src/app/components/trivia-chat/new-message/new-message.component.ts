import { Component, OnInit } from '@angular/core';
import { ChatService } from '../../../services/chat/chat.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'new-message-form',
  templateUrl: './new-message.component.html',
  styleUrls: ['./new-message.component.scss']
})
export class NewMessageComponent {

  private newMessage: string;

  constructor(
    private chatService: ChatService,
    private route: ActivatedRoute
  ) {
    this.newMessage = '';
  }

  sendMessage(): void {
    this.chatService.sendMessage({
      roomName: this.roomName,
      messageBody: this.newMessage
    });
    this.newMessage = '';
  }

  get roomName(): string {
    return this.route.snapshot.params['name'];
  }

}
