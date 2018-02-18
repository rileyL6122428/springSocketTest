import { Injectable } from '@angular/core';
import { Player } from './player';

@Injectable()
export class PlayerFactory {

  fromPOJOMapToList(playerPOJOs: object): Array<Player> {
    let players: Array<Player> = new Array<Player>();
    for(let name in playerPOJOs){
      players.push(this.fromPOJO(playerPOJOs[name]));
    }
    return players.sort(byName);
  }

  fromPOJO(playerPOJO: object): Player {
    return new Player(
      playerPOJO['name'],
      playerPOJO['score']
    );
  }

}

function byName(playerA: Player, playerB: Player) {
  return ( playerA.name > playerB.name ) ? 1 : -1;
}
