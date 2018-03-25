import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TriviaChatComponent } from './trivia-chat.component';
import { FormsModule } from '@angular/forms';
import { ChatMessagesComponent } from './chat-messages/chat-messages.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
  ],
  exports: [ TriviaChatComponent ],
  declarations: [ TriviaChatComponent, ChatMessagesComponent ]
})
export class TriviaChatModule { }
