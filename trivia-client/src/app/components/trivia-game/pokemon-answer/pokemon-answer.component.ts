import { Component, Input, EventEmitter, Output } from '@angular/core';
import { Pokemon } from '../../../domain/pokemon/pokemon';
import { trigger, state, style, animate, transition } from '@angular/animations';


@Component({
  selector: 'pokemon-answer',
  templateUrl: './pokemon-answer.component.html',
  styleUrls: ['./pokemon-answer.component.scss'],
  animations: [
    trigger('fadeOut', [
      transition('* => void', [
        animate('2000ms', style({ 'opacity': '0' })),
      ]),
    ])
  ]
})
export class PokemonAnswerComponent {

  @Input() pokemon: Pokemon;

  private animatingSelection: boolean;
  @Output() private selection: EventEmitter<Pokemon>;

  constructor() {
    this.selection = new EventEmitter<Pokemon>();
  }

  handleSelection(): void {
    this.animatingSelection = true;
    this.selection.emit(this.pokemon);
  }

}
