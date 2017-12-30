import { Injectable } from '@angular/core';
import { GameMessage } from './game-message';

@Injectable()
export class GameMessageFactory {

  mapPOJO(messagePOJO: Object): GameMessage {
    return new GameMessage({
      phase: messagePOJO['typeHeader']
    });
  }

}
