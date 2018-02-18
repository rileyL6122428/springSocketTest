import { Injectable } from '@angular/core';
import { Answer } from './answer';

@Injectable()
export class AnswerFactory {

  fromPOJO(answerPOJO: object): Answer {
    return new Answer(answerPOJO['text']);
  }

  fromPOJOList(answerPOJOList: Array<object>): Array<Answer> {
    if(!answerPOJOList) return null;

    return answerPOJOList.map((questionPOJO: object) => {
      return this.fromPOJO(questionPOJO);
    });
  }

}
