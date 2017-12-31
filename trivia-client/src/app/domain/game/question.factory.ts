import { Injectable } from '@angular/core';
import { Question } from './question';

@Injectable()
export class QuestionFactory {

  fromPOJO(questionPOJO: object): Question {
    return new Question(questionPOJO['text']);
  }

}
