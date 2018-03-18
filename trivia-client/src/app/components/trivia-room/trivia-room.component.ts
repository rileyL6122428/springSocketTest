import { Component, OnInit } from '@angular/core';
import { Room } from '../../domain/room/room';

@Component({
  selector: 'app-trivia-room',
  templateUrl: './trivia-room.component.html',
  styleUrls: ['./trivia-room.component.css']
})
export class TriviaRoomComponent implements OnInit {

  private room: Room;

  constructor() { }

  ngOnInit() {
  }

}
