export class User {

  static fromPOJO(userPOJO: Object): User {
    let user: User = new User();
    user.setName(userPOJO['name']);
    return user;
  }

  private name: string;

  public getName(): string {
    return this.name;
  }

  public setName(name: string): void {
    this.name = name;
  }

}
