import { Component, Input, OnInit } from '@angular/core';
import { ProfessorOak } from '../../../domain/oak/proffesor-oak';
import { Game } from '../../../domain/game/game';

@Component({
  selector: 'prof-oak',
  templateUrl: './prof-oak.component.html',
  styleUrls: ['./prof-oak.component.scss']
})
export class ProfOakComponent implements OnInit {

  @Input() canSpeak: boolean = false;

  private professorOak: ProfessorOak;

  ngOnInit() {
    this.professorOak = new ProfessorOak();
  }

  @Input()
  set game(game: Game) {
    this.professorOak.game = game;
  }

}
