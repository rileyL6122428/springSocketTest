import { Component, OnInit } from '@angular/core';
import { fadeIn } from '../../animations/fadeIn';

@Component({
  selector: 'trivia-game',
  templateUrl: './trivia-game.component.html',
  styleUrls: ['./trivia-game.component.scss'],

  animations: [
    fadeIn({ delay: 5, duration: 1 })
  ]
})
export class TriviaGameComponent { }
