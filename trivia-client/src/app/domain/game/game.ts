import { Player } from '../player/player';

export class Game {

  constructor(params: object) {
    this.phase = params['phase'];
    this.playersToScores = params['playersToScores'];
    this.completedRoundCount = params['completedRoundCount'];
    this.roundCount = params['roundCount'];
  }

  readonly phase: "READY" | "START" | "ASKING_QUESTION";
  readonly playersToScores: Map<Player, number>;
  readonly completedRoundCount: number;
  readonly roundCount: number;

  get completedRoundProportion(): number {
    return this.completedRoundCount / this.roundCount;
  }

}
