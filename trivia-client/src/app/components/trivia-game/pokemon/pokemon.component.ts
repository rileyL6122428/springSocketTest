import { Component , Input } from '@angular/core';
import { Pokemon } from '../../../domain/pokemon/pokemon';

@Component({
  selector: 'pokemon',
  templateUrl: './pokemon.component.html',
  styleUrls: ['./pokemon.component.scss']
})
export class PokemonComponent {

  @Input() private pokemon: Pokemon;

}
