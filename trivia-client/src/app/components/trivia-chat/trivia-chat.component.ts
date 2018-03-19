import { Component, OnInit } from '@angular/core';
import { ChatMessage } from '../../domain/chat/chat-message';

@Component({
  selector: 'trivia-chat',
  templateUrl: './trivia-chat.component.html',
  styleUrls: ['./trivia-chat.component.scss']
})
export class TriviaChatComponent implements OnInit {

  private testChatMessages: Array<ChatMessage>;

  constructor() {
    this.testChatMessages = [
      new ChatMessage({ body: 'SUP', sender: { name: 'Prof Oak' } }),
      new ChatMessage({ body: 'SUP TWO', sender: { name: 'Me' } })
    ];
  }

  ngOnInit() {
  }

}
