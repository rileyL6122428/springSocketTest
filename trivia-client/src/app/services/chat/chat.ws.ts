import { Injectable } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ChatFactory } from '../../domain/chat/chat.factory';
import { ChatStore } from '../../stores/chat/chat.store';
import { StompRService } from '@stomp/ng2-stompjs';
import { StompHeaders } from '@stomp/ng2-stompjs/node_modules/@stomp/stompjs';
import { Chat } from '../../domain/chat/chat';
import { SessionService } from '../session/session.service';

@Injectable()
export class ChatWSUtil {

  constructor(
    private stompService: StompRService,
    private chatFactory: ChatFactory,
    private chatStore: ChatStore,
    private sessionService: SessionService
  ) { }

  listenForChatChanges(roomName: string): Subscription {
    return this.stompService.subscribe(this.listenToChatEnpoint(roomName), this.roomMessageHeaders())
      .map((message) => JSON.parse(message.body))
      .map((messageBody) => this.chatFactory.fromPOJO(messageBody))
      .subscribe((chat: Chat) => this.chatStore.deposit(chat));
  }

  private listenToChatEnpoint(roomName: string): string {
    return `/topic/room/${roomName}/chat`;
  }

  sendMessage(params: { roomName: string, messageBody: string }): void {
    this.stompService.publish(
      this.sendChatMessageEndpoint(params.roomName),
      this.sendChatMessagePayload(params.messageBody),
      this.roomMessageHeaders()
    );
  }

  private sendChatMessageEndpoint(roomName: string): string {
    return `/app/room/${roomName}/send-message`;
  }

  private sendChatMessagePayload(messageBody: string) {
    return JSON.stringify({ messageBody });
  }

  private roomMessageHeaders() {
    return { SESSION_ID: this.sessionService.sessionToken, };
  }

}
