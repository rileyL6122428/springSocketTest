import { Answer } from './answer';
import { Question } from './question';

export class TriviaRound {

  constructor(
    readonly answers: Array<Answer>,
    readonly question: Question
  ) { }

}
