export class Room {

  public static fromPOJO(pojo: Object) {
    let room: Room = new Room();

    room.name = pojo['name'];
    room.maxNumberOfUsers = pojo['maxNumberOfUsers'];

    return room;
  }

  private name: string;
  private maxNumberOfUsers: number;

  public getName(): string {
    return this.name;
  }

  public getMaxNumberOfUsers(): number {
    return this.maxNumberOfUsers;
  }

}
