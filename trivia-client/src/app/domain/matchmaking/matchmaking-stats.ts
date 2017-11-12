import { Room } from '../room/room';

export class MatchmakingStats {

  constructor(params: object) {
    this.rooms = params['rooms'];
    this.unplacedUserTotal = params['unplacedUserTotal'];
  }

  readonly rooms: Array<Room>;
  readonly unplacedUserTotal: number;

}
