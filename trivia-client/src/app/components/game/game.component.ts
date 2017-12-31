import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { RoomService } from '../../services/room.service';
import { Subscription } from 'rxjs/Subscription';
import { Game } from '../../domain/game/game';

@Component({
  selector: 'game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit, OnDestroy {

  @Input()
  private roomName: string;

  private game: Game;
  private subscriptions: Array<Subscription>;

  constructor(
    private roomService: RoomService
  ) { }

  ngOnInit(): void {
    this.subscriptions = [
      this.roomService.getGameStompListener(this.roomName)
        .subscribe((game: Game) => this.game = game )
    ];
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription: Subscription) => {
      subscription.unsubscribe();
    });
  }

}
