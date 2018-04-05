import { Injectable } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ChatFactory } from '../../domain/chat/chat.factory';
import { ChatStore } from '../../stores/chat/chat.store';
import { StompRService } from '@stomp/ng2-stompjs';
import { StompHeaders } from '@stomp/ng2-stompjs/node_modules/@stomp/stompjs';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { Chat } from '../../domain/chat/chat';
import { SessionService } from '../session/session.service';

@Injectable()
export class ChatWSUtil {

  constructor(
    private stompService: StompRService,
    private chatFactory: ChatFactory,
    private chatStore: ChatStore,
    private cookieService: CookieService,
    private sessionService: SessionService
  ) { }

  listenForChatChanges(roomName: string): Subscription {
    return this.stompService.subscribe(`/topic/room/${roomName}/chat`, { SESSION_ID: this.sessionToken })
      .map((message) => JSON.parse(message.body))
      .map((messageBody) => this.chatFactory.fromPOJO(messageBody))
      .subscribe((chat: Chat) => this.chatStore.deposit(chat));
  }

  sendMessage(params: { roomName: string, messageBody: string }): void {
    const endpoint = `/app/room/${params.roomName}/send-message`;
    const message = JSON.stringify({ messageBody: params.messageBody });

    this.stompService.publish(endpoint, message, {
      SESSION_ID: this.sessionService.sessionToken,
      roomName: params.roomName
    });
  }

  private get sessionToken(): string {
    return this.sessionService.sessionToken;
  }

}
