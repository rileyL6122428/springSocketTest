import { TestBed, inject } from '@angular/core/testing';
import { MatchmakingStats } from './matchmaking-stats';
import { MatchmakingStatsFactory } from './matchmaking-stats.factory';
import { Room } from '../room/room';
import { DomainFactoryModule } from '../factory.module';
import { RoomFactory } from '../room/room.factory';
import { User } from '../user/user';

describe('matchmaking-stats', () => {

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ DomainFactoryModule ]
    });
  });

  describe('#createNewStats', () => {
    it("returns an instance of MatchmakingStats",
      inject([MatchmakingStatsFactory], (statsFactory: MatchmakingStatsFactory) => {
        let payload: object = { rooms: {}, userTotal: 0 };
        let matchmakingStats: MatchmakingStats = statsFactory.createNewStats(payload);
        expect(matchmakingStats).toEqual(jasmine.any(MatchmakingStats));
      }
    ));

    it("it generates an alphatized list of rooms by delegating to RoomFactory",
      inject([MatchmakingStatsFactory, RoomFactory], (statsFactory: MatchmakingStatsFactory, roomFactory: RoomFactory) => {
        let mappedRoomsMock: any = [];
        spyOn(roomFactory, "fromPOJOMapToList").and.returnValue(mappedRoomsMock);

        let payload: object = { rooms: {}, userTotal: 0 };

        let matchmakingStats: MatchmakingStats = statsFactory.createNewStats(payload);

        expect(roomFactory.fromPOJOMapToList).toHaveBeenCalledWith(payload['rooms']);
        expect(matchmakingStats.rooms).toBe(mappedRoomsMock);
      }
    ));

    it("sets the unplaced user total by subtracting the total user count from the number of users in each room",
      inject([MatchmakingStatsFactory, RoomFactory], (statsFactory: MatchmakingStatsFactory, roomFactory: RoomFactory) => {
        let mockRoomOne: any = { users: new Map<string, User>() };
        mockRoomOne.users.set("tommy", new User({name: "tommy"}));
        mockRoomOne.users.set("bobby", new User({name: "bobby"}));

        let mockRoomTwo: any = { users: new Map<string, User>() };
        mockRoomTwo.users.set("clara", new User({name: "clara"}));
        mockRoomTwo.users.set("tammy", new User({name: "tammy"}));
        mockRoomTwo.users.set("pedro", new User({name: "pedro"}));

        spyOn(roomFactory, "fromPOJOMapToList").and.returnValue([mockRoomOne, mockRoomTwo]);

        let payload: object = { rooms: {}, userTotal: 6 };
        let matchmakingStats: MatchmakingStats = statsFactory.createNewStats(payload);

        expect(matchmakingStats.unplacedUserTotal).toEqual(1);
      }
    ));
  });

});
