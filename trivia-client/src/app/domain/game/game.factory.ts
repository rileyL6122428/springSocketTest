import { Injectable } from '@angular/core';
import { Game } from './game';
import { PlayerFactory } from '../player/player.factory';
import { AnswerFactory } from './answer.factory';
import { QuestionFactory } from './question.factory';

@Injectable()
export class GameFactory {

  constructor(
    private playerFactory: PlayerFactory,
    private answerFactory: AnswerFactory,
    private questionFactory: QuestionFactory
  ) { }

  mapPOJO(messagePOJO: Object): Game {
    return new Game({
      phase: messagePOJO['typeHeader'],
      playersToScores: this.playerFactory.playerToScoreMap(messagePOJO['playerScores']),
      completedRoundCount: messagePOJO['completedRoundCount'],
      roundCount: messagePOJO['totalRoundCount'],
      currentQuestion: this.questionFactory.fromPOJO(messagePOJO['currentQuestion']),
      currentAnswers: this.answerFactory.fromPOJOList(messagePOJO['currentQuestionAnswers'])
    });
  }

}
