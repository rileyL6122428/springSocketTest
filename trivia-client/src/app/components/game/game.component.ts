import { Component, Input, OnInit } from '@angular/core';
import { RoomService } from '../../services/room.service';
import { Game } from '../../domain/game/game';
import { Answer } from '../../domain/game/answer';
import { SubscribingComponent } from '../base/subscribing.component';

@Component({
  selector: 'game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent extends SubscribingComponent implements OnInit {

  @Input()
  private roomName: string;

  private game: Game;

  constructor(
    private roomService: RoomService
  ) {
    super();
  }

  ngOnInit(): void {
    this.addSubscriptions(
      this.roomService.getGameStompListener(this.roomName)
        .subscribe((game: Game) => {
          this.game = game
        })
    );
  }

  submitAnswer(answer: Answer): void {
    this.roomService.submitGameAnswer({
      roomName: this.roomName,
      answer: answer
    });
  }

}
