import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';
import { ChatFactory } from '../../domain/chat/chat.factory';
import { ChatStore } from '../../stores/chat/chat.store';

@Injectable()
export class ChatHttpUtil {

  constructor(
    private http: Http,
    private chatFactory: ChatFactory,
    private chatStore: ChatStore
  ) { }

  fetchChat(roomName: string): Subscription {
    return this.http.get(`/room/${roomName}/chat`).subscribe((response) => {
      const chat = this.chatFactory.fromPOJO(response.json());
      this.chatStore.deposit(chat);
    });
  }

}
