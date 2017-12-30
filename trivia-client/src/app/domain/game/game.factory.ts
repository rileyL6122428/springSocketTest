import { Injectable } from '@angular/core';
import { Game } from './game';
import { PlayerFactory } from '../player/player.factory';

@Injectable()
export class GameFactory {

  constructor(
    private playerFactory: PlayerFactory
  ) { }

  mapPOJO(messagePOJO: Object): Game {
    debugger
    return new Game({
      phase: messagePOJO['typeHeader'],
      playersToScores: this.playerFactory.playerToScoreMap(messagePOJO['playerScores'])
    });
  }

}
