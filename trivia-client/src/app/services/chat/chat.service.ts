import { Injectable } from '@angular/core';
import { ChatStore } from '../../stores/chat/chat.store';
import { Subscription } from 'rxjs/Subscription';
import { Http } from '@angular/http';
import { StompRService } from '@stomp/ng2-stompjs';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { ChatFactory } from '../../domain/chat/chat.factory';

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
      // this.listenForMatchmakingStats()
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

}
