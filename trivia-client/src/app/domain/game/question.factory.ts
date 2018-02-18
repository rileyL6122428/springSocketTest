import { Injectable } from '@angular/core';
import { Question } from './question';

@Injectable()
export class QuestionFactory {

  fromPOJO(questionPOJO: object): Question {
    return (questionPOJO) ? new Question(questionPOJO['text']) : null;
  }

}
