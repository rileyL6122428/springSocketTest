import { Injectable } from '@angular/core';
import { Store } from '../base/base.store';
import { Chat } from '../../domain/chat/chat';

@Injectable()
export class ChatStore extends Store<Chat> {

  constructor() {
    super();
  }

  getByName(name: string): Chat {
    return this.records.get(name);
  }

}
