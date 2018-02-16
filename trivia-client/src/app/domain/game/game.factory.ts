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
    debugger
    return new Game(
      messagePOJO['phase'],
      this.playerFactory.fromPOJOMapToList(messagePOJO['players']),
      messagePOJO['currentRoundNumber'],
      messagePOJO['rountCount'],
      messagePOJO['currentQuestion'],
      messagePOJO['currentAnswers']
    );
  }

}
