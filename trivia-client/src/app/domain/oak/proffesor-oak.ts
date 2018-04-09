import { Game } from '../game/game';
import { Phase } from '../game/phase';


export class ProfessorOak {

  private _game: Game;

  private narrationMap: Map<Phase, string>;

  constructor() {
    this.narrationMap = new Map<Phase, string>()
      .set('WAITING_FOR_PLAYERS', `
        It looks like there aren't enough trainers to start another game.
        Don't leave! A Poke Trivia trainer could appear anytime!
      `)
      .set('READY', `
        Trainers have arrived! Let's start!
        A round of dreams and adventures with Pokémon begins!
      `);
  }

  get narration(): string {
    return this.narrationMap.get(this._game.phase);
  }

  set game(game: Game) {
    this._game = game;
  }

}
