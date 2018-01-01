import { Player } from '../player/player';
import { Question } from './question';
import { Answer } from './answer';

export class Game {

  constructor(params: object) {
    this.phase = params['phase'];
    this.playersToScores = params['playersToScores'];
    this.completedRoundCount = params['completedRoundCount'];
    this.roundCount = params['roundCount'];
    this.currentQuestion = params['currentQuestion'];
    this.currentAnswers = params['currentAnswers'];
  }

  readonly phase: "READY" | "START" | "ASKING_QUESTION" | "QUESTION_CLOSED";
  readonly playersToScores: Map<Player, number>;
  readonly completedRoundCount: number;
  readonly roundCount: number;
  readonly currentQuestion: Question;
  readonly currentAnswers: Array<Answer>;

  get completedRoundProportion(): number {
    return this.completedRoundCount / this.roundCount;
  }

}
