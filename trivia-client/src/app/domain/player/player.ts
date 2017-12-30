export class Player {

  constructor(params: object) {
    this.name = params['name'];
  }

  readonly name: string;
}
