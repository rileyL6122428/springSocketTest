import { Component, Input } from '@angular/core';
import { ProfessorOak } from '../../../domain/oak/proffesor-oak';
import { Game } from '../../../domain/game/game';

@Component({
  selector: 'prof-oak',
  templateUrl: './prof-oak.component.html',
  styleUrls: ['./prof-oak.component.scss']
})
export class ProfOakComponent {

  @Input() canSpeak: boolean = false;

  private professorOak: ProfessorOak;

  constructor() {
    this.professorOak = new ProfessorOak();
  }

  get narration(): string {
    return this.professorOak.narration;
  }

  @Input()
  set game(game: Game) {
    this.professorOak.game = game;
  }

}
