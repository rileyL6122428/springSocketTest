export class Pokemon {
  constructor(readonly name: string) { }

  equals(otherPokemon: Pokemon): boolean {
    return this.name === otherPokemon.name;
  }
}
