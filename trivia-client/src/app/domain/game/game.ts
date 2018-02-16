import { Player } from '../player/player';
import { Question } from './question';
import { Answer } from './answer';
import { Phase } from './phase';

export class Game {

  constructor(
    readonly phase: Phase,
    readonly players: Array<Player>,
    readonly completedRoundCount: number,
    readonly roundCount: number,
    readonly currentQuestion: Question,
    readonly currentAnswers: Array<Answer>
  ) {  }


  get completedRoundProportion(): number {
    return this.completedRoundCount / this.roundCount;
  }

}
