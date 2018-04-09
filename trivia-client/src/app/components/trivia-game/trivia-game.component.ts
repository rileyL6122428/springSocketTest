import { Component, OnInit } from '@angular/core';
import { fadeIn } from '../../animations/fadeIn';
import { Game } from '../../domain/game/game';
import { Player } from '../../domain/player/player';

@Component({
  selector: 'trivia-game',
  templateUrl: './trivia-game.component.html',
  styleUrls: ['./trivia-game.component.scss'],

  animations: [
    fadeIn({ delay: 6, duration: 1 })
  ]
})
export class TriviaGameComponent {

  // FOR MOCK DESIGN ONLY
  private mockGameStateIndex: number = 0;

  private gameStates: Array<Game> = [
    new Game(
      'WAITING_FOR_PLAYERS',
      [new Player('Red', 0)],
      null,
      null,
      null,
      null
    ),
    new Game(
      'READY',
      [new Player('Red', 0), new Player('Blue', 0)],
      null,
      null,
      null,
      null
    )
  ];

  private speaking: boolean = false;

  incrementMockGameState(): void {
    this.mockGameStateIndex =
      Math.min(this.gameStates.length - 1, this.mockGameStateIndex + 1);
  }

  beginSpeechFeed(): void {
    this.speaking = true;
  }

}
