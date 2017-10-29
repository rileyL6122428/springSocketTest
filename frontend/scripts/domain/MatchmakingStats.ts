import { Room } from './Room';

export class MatchmakingStats {

  readonly rooms: Array<Room>;
  readonly unplacedUsersTotal: number;

  constructor(messageBody: Object) {
    let rooms: Array<Room> = Room.fromPOJOMapToList(messageBody['rooms']);
    let userTotal: number = messageBody['userTotal'];

    this.rooms = rooms;
    this.unplacedUsersTotal = this.calculateUnplacedUsersTotal(userTotal, rooms);
  }

  private calculateUnplacedUsersTotal(userTotal: number, rooms: Array<Room>) {
    return userTotal - this.placedUserTotal(rooms);
  }

  private placedUserTotal(rooms: Array<Room>): number {
    let placedUserTotal = 0;

    rooms.forEach((room: Room) => {
      placedUserTotal += room.getTotalNumberOfUsers();
    });

    return placedUserTotal;
  }
}
