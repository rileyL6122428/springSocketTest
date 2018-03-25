import { Component, OnInit } from '@angular/core';
import { ChatMessage } from '../../domain/chat/chat-message';
import { ChatService } from '../../services/chat/chat.service';
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
    private chatService: ChatService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.listenToChatStream();
    this.makeChatActive();
  }

  private listenToChatStream(): void {
    this.chatService.stream(this.roomName, (chatStore: ChatStore) => {
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
