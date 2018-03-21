import { Injectable } from '@angular/core';
import { ChatStore } from '../../stores/chat/chat.store';
import { Subscription } from 'rxjs/Subscription';
import { Http } from '@angular/http';
import { StompRService } from '@stomp/ng2-stompjs';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { ChatFactory } from '../../domain/chat/chat.factory';
import { Chat } from '../../domain/chat/chat';
import { StompHeaders } from '@stomp/ng2-stompjs/node_modules/@stomp/stompjs';

@Injectable()
export class ChatService {

  constructor(
    private chatStore: ChatStore,
    private http: Http,
    private stompService: StompRService,
    private cookieService: CookieService,
    private chatFactory: ChatFactory
  ) { }

  stream(roomName: string, onUpdate: (store: ChatStore) => void): { unsubscribe: () => void } {
    onUpdate(this.chatStore);

    const subs = [
      this.placeStoreListener(onUpdate),
      this.fetchChat(roomName),
      this.listenForChatChanges(roomName)
    ];

    return {
      unsubscribe() { subs.forEach((sub) => sub.unsubscribe()); }
    };
  }

  private placeStoreListener(listener: any): Subscription {
    return this.chatStore.updates.subscribe(() => {
      listener(this.chatStore);
    });
  }

  private fetchChat(roomName: string): Subscription {
    return this.http.get(`/room/${roomName}/chat`).subscribe((response) => {
      const chat = this.chatFactory.fromPOJO(response.json());
      this.chatStore.deposit(chat);
    });
  }

  private listenForChatChanges(roomName: string): Subscription {
    return this.stompService.subscribe(`/topic/room/${roomName}/chat`, this.getStompHeaders())
      .map((message) => {
        const messageBody = JSON.parse(message.body);
        return this.chatFactory.fromPOJO(messageBody);
      })
      .subscribe((chat: Chat) => {
        this.chatStore.deposit(chat);
      });
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
