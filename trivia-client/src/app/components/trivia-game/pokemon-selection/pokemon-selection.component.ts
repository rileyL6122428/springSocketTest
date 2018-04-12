import { Component, Input } from '@angular/core';
import { Pokemon } from '../../../domain/pokemon/pokemon';

@Component({
  selector: 'pokemon-selection',
  templateUrl: './pokemon-selection.component.html',
  styleUrls: ['./pokemon-selection.component.scss']
})
export class PokemonSelectionComponent {

  @Input() private pokemon: Array<Pokemon>;

}
