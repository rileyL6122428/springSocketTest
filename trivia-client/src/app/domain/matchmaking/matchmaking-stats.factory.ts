import { Injectable } from '@angular/core';
import { MatchmakingStats } from './matchmaking-stats';
import { Room } from '../room/room';
import { RoomFactory } from '../room/room.factory';

@Injectable()
export class MatchmakingStatsFactory {

  constructor(
    private roomFactory: RoomFactory
  ) { }

  fromPOJO(matchmakingPayload: Object): MatchmakingStats {
    let rooms: Array<Room> = this.roomFactory.fromPOJOMapToList(matchmakingPayload["rooms"]);
    let unplacedUserTotal = this.totalUsersInMatchmaking(rooms, matchmakingPayload["userTotal"]);

    return new MatchmakingStats({ rooms, unplacedUserTotal });
  }

  private totalUsersInMatchmaking(rooms: Array<Room>, userTotal: number): number {
    let totalUsersInMatchmaking: number = userTotal;

    rooms.forEach((room: Room) => {
      totalUsersInMatchmaking -= room.users.size;
    });

    return totalUsersInMatchmaking;
  }

}
