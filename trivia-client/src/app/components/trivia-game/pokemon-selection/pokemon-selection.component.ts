import { Component, Input, OnChanges } from '@angular/core';
import { Pokemon } from '../../../domain/pokemon/pokemon';
import { Game } from '../../../domain/game/game';
import { User } from '../../../domain/user/user';

@Component({
  selector: 'pokemon-selection',
  templateUrl: './pokemon-selection.component.html',
  styleUrls: ['./pokemon-selection.component.scss']
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

  private animatePokemonSelection(): void {
    if (this.game.guessedCorrectly(this.user)) {
      this.answerState = 'CORRECT';
    } else {
      this.answerState = 'INCORRECT';
    }
  }
}
