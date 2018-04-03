import { Component, OnInit } from '@angular/core';
import { ChatWSUtil } from '../../../services/chat/chat.ws';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'new-message-form',
  templateUrl: './new-message.component.html',
  styleUrls: ['./new-message.component.scss']
})
export class NewMessageComponent {

  private newMessage: string;

  constructor(
    private chatWSUtil: ChatWSUtil,
    private route: ActivatedRoute
  ) {
    this.newMessage = '';
  }

  sendMessage(): void {
    this.chatWSUtil.sendMessage({
      roomName: this.roomName,
      messageBody: this.newMessage
    });
    this.newMessage = '';
  }

  get roomName(): string {
    return this.route.snapshot.params['name'];
  }

}
