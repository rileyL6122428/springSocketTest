import { Component, Input, OnChanges } from '@angular/core';
import { Pokemon } from '../../../domain/pokemon/pokemon';
import { Game } from '../../../domain/game/game';
import { User } from '../../../domain/user/user';
import { trigger, state, style, animate, transition } from '@angular/animations';

@Component({
  selector: 'pokemon-selection',
  templateUrl: './pokemon-selection.component.html',
  styleUrls: ['./pokemon-selection.component.scss'],
  animations: [
    trigger('fadeOut', [
      state('false', style({ 'opacity': '1' })),
      state('true', style({ 'opacity': '0' })),
      transition('false => true', [
        animate('200ms', style({ 'opacity': '0' })),
      ]),
    ])
  ]
})
export class PokemonSelectionComponent implements OnChanges {

  @Input() private game: Game;
  @Input() private user: User;

  private selectedPokemon: Pokemon;

  private answerState: 'CORRECT' | 'INCORRECT' | null;

  ngOnChanges() {
    switch (this.game.phase) {
      case 'ANSWERING_QUESTION':
        this.answerState = null;
        break;
      case 'CHECKING_ANSWERS':
        this.animatePokemonSelection();
        break;
    }
  }

  handleSelect(pokemon: Pokemon) {
    this.selectedPokemon = pokemon;
  }

  wasRejected(pokemon: Pokemon): string {
    return (this.selectedPokemon &&
      !this.selectedPokemon.equals(pokemon)) ? 'true' : 'false';
  }

  private animatePokemonSelection(): void {
    if (this.game.guessedCorrectly(this.user)) {
      this.answerState = 'CORRECT';
    } else {
      this.answerState = 'INCORRECT';
    }
  }
}
