import { Component, OnInit } from '@angular/core';
import { ChatMessage } from '../../domain/chat/chat-message';
import { ChatService } from '../../services/chat/chat.service';
import { ActivatedRoute } from '@angular/router';
import { ChatStore } from '../../stores/chat/chat.store';
import { Chat } from '../../domain/chat/chat';

@Component({
  selector: 'trivia-chat',
  templateUrl: './trivia-chat.component.html',
  styleUrls: ['./trivia-chat.component.scss']
})
export class TriviaChatComponent implements OnInit {

  private chat: Chat;

  constructor(
    private chatService: ChatService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.chatService.stream(this.roomName, (chatStore: ChatStore) => {
      this.chat = chatStore.getByName(this.roomName);
    });
  }

  get roomName(): string {
    return this.route.snapshot.params['name'];
  }

}
