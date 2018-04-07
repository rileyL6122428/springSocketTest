import { Injectable } from '@angular/core';
import { ChatStore } from '../../stores/chat/chat.store';
import { Subscription } from 'rxjs/Subscription';
import { StreamSubscription } from '../stream/stream-subscription';
import { ChatHttpUtil } from './chat.http';
import { ChatWsUtil } from './chat.ws';

@Injectable()
export class ChatStream {

  constructor(
    private chatStore: ChatStore,
    private chatHttpUtil: ChatHttpUtil,
    private chatWSUtil: ChatWsUtil
  ) { }

  subscribe(roomName: string, onUpdate: (store: ChatStore) => void): StreamSubscription {
    return new StreamSubscription([
      this.placeStoreListener(onUpdate),
      this.fetchChat(roomName),
      this.listenForChatChanges(roomName)
    ]);
  }

  private placeStoreListener(listener: any): Subscription {
    return this.chatStore.placeListener(listener);
  }

  private fetchChat(roomName: string): Subscription {
    return this.chatHttpUtil.fetchChat(roomName);
  }

  private listenForChatChanges(roomName: string): Subscription {
    return this.chatWSUtil.listenForChatChanges(roomName);
  }

}
