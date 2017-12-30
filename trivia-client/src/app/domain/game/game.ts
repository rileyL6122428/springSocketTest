import { Player } from '../player/player';

export class Game {

  constructor(params: object) {
    this.phase = params['phase'];
    this.playersToScores = params['playersToScores'];
  }

  readonly phase: "GAME_READY" | "GAME_START";
  readonly playersToScores: Map<Player, number>;

}
