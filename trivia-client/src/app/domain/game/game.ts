import { Player } from '../player/player';
import { Question } from './question';
import { Answer } from './answer';
import { Phase } from './phase';
import { Pokemon } from '../pokemon/pokemon';
import { User } from '../user/user';

export class Game {

  constructor(
    readonly phase: Phase,
    readonly players: Array<Player>,
    readonly currentRoundNumber: number,
    readonly roundCount: number,
    readonly currentQuestion: Question,
    // readonly currentAnswers: Array<Answer>
    readonly pokemon: Array<Pokemon>,
    readonly correctPlayers: Array<Player>
  ) {  }


  get completedRoundProportion(): number {
    if (this.phase === 'FINISHED') {
      return 1;
    } else {
      return Math.max((this.currentRoundNumber - 1) / this.roundCount, 0);
    }
  }

  guessedCorrectly(user: User): boolean {
    return this.correctPlayers.some((player: Player) => {
      return user.name === player.name;
    });
  }

}
