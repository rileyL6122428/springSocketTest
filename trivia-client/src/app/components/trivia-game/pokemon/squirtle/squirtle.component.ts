import { Component, Input } from '@angular/core';
import { pokemonAnimations } from '../pokemon.animations';

@Component({
  selector: 'squirtle',
  templateUrl: './squirtle.component.html',
  styleUrls: ['../pokemon.component.scss'],
  animations: pokemonAnimations
})
export class SquirtleComponent {

  @Input() animatingSelection: boolean;

  get captureState(): string {
    return this.animatingSelection ? 'attempting' : 'not-attempting';
  }

}
