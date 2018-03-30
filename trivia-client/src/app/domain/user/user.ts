export class User {

  constructor(params: Object) {
    this.name = params['name'];
  }

  readonly name: string;
}
