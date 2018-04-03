import { Injectable } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ChatFactory } from '../../domain/chat/chat.factory';
import { ChatStore } from '../../stores/chat/chat.store';
import { StompRService } from '@stomp/ng2-stompjs';
import { StompHeaders } from '@stomp/ng2-stompjs/node_modules/@stomp/stompjs';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { Chat } from '../../domain/chat/chat';

@Injectable()
export class ChatWSUtil {

  constructor(
    private stompService: StompRService,
    private chatFactory: ChatFactory,
    private chatStore: ChatStore,
    private cookieService: CookieService
  ) { }

  listenForChatChanges(roomName: string): Subscription {
    return this.stompService.subscribe(`/topic/room/${roomName}/chat`, this.getStompHeaders())
      .map((message) => JSON.parse(message.body))
      .map((messageBody) => this.chatFactory.fromPOJO(messageBody))
      .subscribe((chat: Chat) => this.chatStore.deposit(chat));
  }

  private getStompHeaders(): StompHeaders {
    return { SESSION_ID: this.cookieService.get('TRIVIA_SESSION_COOKIE') };
  }

  sendMessage(params: { roomName: string, messageBody: string }): void {
    const endpoint = `/app/room/${params.roomName}/send-message`;
    const message = JSON.stringify({ messageBody: params.messageBody });

    this.stompService.publish(endpoint, message, {
      SESSION_ID: this.cookieService.get('TRIVIA_SESSION_COOKIE'),
      roomName: params.roomName
    });
  }

}
