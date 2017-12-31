import { Injectable } from '@angular/core';
import { Answer } from './answer';

@Injectable()
export class AnswerFactory {

  fromPOJO(answerPOJO: object): Answer {
    return new Answer(answerPOJO['text']);
  }

  fromPOJOList(answerPOJOList: Array<object>): Array<Answer> {
    let answers: Array<Answer> = new Array<Answer>();

    answerPOJOList.forEach((questionPOJO: object) => {
      answers.push(this.fromPOJO(questionPOJO));
    });

    return answers;
  }

}
