import { Component, Input } from '@angular/core';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { stagger } from '@angular/core/src/animation/dsl';
import { pokemonAnimations } from '../pokemon.animations';

@Component({
  selector: 'charmander',
  templateUrl: './charmander.component.html',
  styleUrls: ['../pokemon.component.scss'],
  animations: pokemonAnimations
})
export class CharmanderComponent {

  @Input() animatingSelection: boolean;

  get captureState(): string {
    return this.animatingSelection ? 'attempting' : 'not-attempting';
  }

}
