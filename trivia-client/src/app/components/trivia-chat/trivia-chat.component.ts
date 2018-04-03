import { Component, OnInit } from '@angular/core';
import { ChatMessage } from '../../domain/chat/chat-message';
import { ChatStream } from '../../services/chat/chat.stream';
import { ActivatedRoute } from '@angular/router';
import { ChatStore } from '../../stores/chat/chat.store';
import { Chat } from '../../domain/chat/chat';
import { trigger, state, style, animate, transition, query } from '@angular/animations';

@Component({
  selector: 'trivia-chat',
  templateUrl: './trivia-chat.component.html',
  styleUrls: ['./trivia-chat.component.scss'],
})
export class TriviaChatComponent implements OnInit {

  private chat: Chat;
  private chatActive: boolean;

  constructor(
    private chatStream: ChatStream,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.listenToChatStream();
    this.makeChatActive();
  }

  private listenToChatStream(): void {
    this.chatStream.subscribe(this.roomName, (chatStore: ChatStore) => {
      this.chat = chatStore.getByName(this.roomName);
    });
  }

  private makeChatActive(): void {
    setTimeout(() => this.chatActive = true, 1);
  }

  get roomName(): string {
    return this.route.snapshot.params['name'];
  }

}
