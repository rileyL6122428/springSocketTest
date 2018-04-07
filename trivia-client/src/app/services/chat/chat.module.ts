import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ChatHttpUtil } from './chat.http';
import { ChatStream } from './chat.stream';
import { ChatWsUtil } from './chat.ws';

@NgModule({
  providers: [
    ChatHttpUtil,
    ChatWsUtil,
    ChatStream
  ]
})
export class ChatServiceModule { }
