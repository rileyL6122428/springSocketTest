import { Game } from '../game/game';
import { Phase } from '../game/phase';


export class ProfessorOak {

  private _game: Game;

  get narration(): string {
    return (this._game) ? this.narrationByPhase() : '';
  }

  private narrationByPhase(): string {
    switch (this._game.phase) {
      case ('WAITING_FOR_PLAYERS'): return this.waitingNarration;
      case ('READY'): return this.readyToStartNarration;
      default: return 'error occurred';
    }
  }

  private get waitingNarration(): string {
    return `
        It looks like there aren't enough trainers to start another game.
        Don't leave! A Poke Trivia trainer could appear anytime!
      `;
  }

  private get readyToStartNarration(): string {
    return `
        Trainers have arrived! Let's start!
        A round of dreams and adventures with Pokémon begins!
      `;
  }

  set game(game: Game) {
    this._game = game;
  }

}
