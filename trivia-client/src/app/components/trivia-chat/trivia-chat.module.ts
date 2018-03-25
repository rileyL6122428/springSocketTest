import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TriviaChatComponent } from './trivia-chat.component';
import { FormsModule } from '@angular/forms';
import { ChatMessagesComponent } from './chat-messages/chat-messages.component';
import { ChatDexBottomComponent } from './chat-dex-bottom/chat-dex-bottom.component';
import { NewMessageComponent } from './new-message/new-message.component';
import { ChatDexTopComponent } from './chat-dex-top/chat-dex-top.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
  ],
  exports: [ TriviaChatComponent ],
  declarations: [
    TriviaChatComponent,
    ChatMessagesComponent,
    ChatDexBottomComponent,
    NewMessageComponent,
    ChatDexTopComponent
  ]
})
export class TriviaChatModule { }
