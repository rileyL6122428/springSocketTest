import { Component, Input } from '@angular/core';
import { Pokemon } from '../../../domain/pokemon/pokemon';

@Component({
  selector: 'pokemon-answer',
  templateUrl: './pokemon-answer.component.html',
  styleUrls: ['./pokemon-answer.component.scss']
})
export class PokemonAnswerComponent {

  @Input() pokemon: Pokemon;

  private animatingSelection: boolean;

  handleSelection(): void {
    this.animatingSelection = true;
  }

}
