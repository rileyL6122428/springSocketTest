import { Component, OnInit } from '@angular/core';
import { Room } from '../../domain/room/room';
import { ChatMessage } from '../../domain/chat/chat-message';

@Component({
  selector: 'app-trivia-room',
  templateUrl: './trivia-room.component.html',
  styleUrls: ['./trivia-room.component.scss']
})
export class TriviaRoomComponent implements OnInit {

  private room: Room;

  constructor() { }

  ngOnInit() {
  }

}
