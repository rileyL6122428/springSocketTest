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

  readonly phase: "READY" | "START" | "ASKING_QUESTION" | "QUESTION_CLOSED" | "FINISHED";
  readonly playersToScores: Map<Player, number>;
  readonly completedRoundCount: number;
  readonly roundCount: number;
  readonly currentQuestion: Question;
  readonly currentAnswers: Array<Answer>;

  get completedRoundProportion(): number {
    return this.completedRoundCount / this.roundCount;
  }

  get highestScorers(): Array<Player> {
    let highScore: number = this.highScore;
    let highestScorers: Array<Player> = new Array<Player>();

    this.playersToScores.forEach((score: number, player: Player) => {
      if(score === highScore)
        highestScorers.push(player);
    });

    return highestScorers;
  }

  get highScore(): number {
    let highScore: number = 0;

    this.playersToScores.forEach((score: number, player: Player) => {
      if(score > highScore)
        highScore = score
    });

    return highScore;
  }

}
