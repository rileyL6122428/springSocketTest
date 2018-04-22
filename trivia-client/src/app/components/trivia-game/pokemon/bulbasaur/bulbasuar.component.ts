import { Component, Input } from '@angular/core';
import { pokemonAnimations } from '../pokemon.animations';

@Component({
  selector: 'bulbasaur',
  templateUrl: './bulbasaur.component.html',
  styleUrls: ['../pokemon.component.scss'],
  animations: pokemonAnimations
})
export class BulbasaurComponent {

  @Input() animatingSelection: boolean;

  get captureState(): string {
    return this.animatingSelection ? 'attempting' : 'not-attempting';
  }

}
