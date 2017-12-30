export class GameMessage {

  constructor(params: object) {
    this.phase = params['phase'];
  }

  readonly phase: "GAME_READY" | "GAME_START";

}
