import { Injectable } from '@angular/core';
import { Player } from './player';

@Injectable()
export class PlayerFactory {

  playerToScoreMap(scoreMapPOJO: object): Map<Player, number> {
    let scoreMap: Map<Player, number> = new Map<Player, number>();

    for(let playerName in scoreMapPOJO) {
      scoreMap.set( this.fromName(playerName), scoreMapPOJO[playerName] );
    }

    return scoreMap;
  }

  fromName(name: string): Player {
    return new Player({ name });
  }

  fromPOJO(playerPOJO: object): Player {
    return new Player({
      name: playerPOJO['name']
    });
  }

}
