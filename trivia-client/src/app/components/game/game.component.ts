import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import { RoomService } from '../../services/room.service';
import { Subscription } from 'rxjs/Subscription';
import { GameMessage } from '../../domain/game-message/game-message';

@Component({
  selector: 'game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit, OnDestroy {

  @Input()
  private roomName: string;

  private subscriptions: Array<Subscription>;

  constructor(
    private roomService: RoomService
  ) { }

  ngOnInit(): void {
    debugger
    this.subscriptions = [
      this.roomService.getGameStompListener(this.roomName)
      .subscribe((gameMessage: GameMessage) => {
        debugger
        console.log(gameMessage);
      })
    ]
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription: Subscription) => {
      subscription.unsubscribe();
    });
  }

}
