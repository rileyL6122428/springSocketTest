export class Room {

  public static fromPOJO(pojo: Object) {
    let room: Room = new Room();

    room.name = pojo['name'];
    room.maxNumberOfUsers = pojo['maxNumberOfUsers'];
    room.totalNumberOfUsers = pojo['totalNumberOfUsers'];

    return room;
  }

  private name: string;
  private maxNumberOfUsers: number;
  private totalNumberOfUsers: number;

  public getName(): string {
    return this.name;
  }

  public getMaxNumberOfUsers(): number {
    return this.maxNumberOfUsers;
  }

  public getTotalNumberOfUsers(): number {
    return this.totalNumberOfUsers;
  }

}
