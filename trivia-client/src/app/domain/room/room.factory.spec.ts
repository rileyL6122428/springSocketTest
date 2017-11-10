import { TestBed, inject } from '@angular/core/testing';
import { RoomFactory } from './room.factory';
import { RoomMessageFactory } from '../chat-room-message/room-message.factory';
import { RoomMessage } from '../chat-room-message/room-message';
import { UserFactory } from '../user/user.factory';
import { User } from '../user/user';
import { Room } from './room';

describe('RoomFactory', () => {

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        RoomFactory,
        RoomMessageFactory,
        UserFactory
      ]
    });
  });

  describe('#fromPOJO', () => {

    let roomPOJO: object;

    beforeEach(() => {
      roomPOJO = {
        name: "EXAMPLE_NAME",
        maxNumberOfUsers: 5,
        messages: new Array<Object>(),
        users: {}
      };
    });

    it("maps the user payload by delegating to the UserFactory",
      inject([RoomFactory, UserFactory],
        (roomFactory: RoomFactory, userFactory: UserFactory) => {
        let mockMappedUsers: Map<string, User> = new Map<string, User>();
        spyOn(userFactory, "mapPOJOMap").and.returnValue(mockMappedUsers);

        let room: Room = roomFactory.fromPOJO(roomPOJO);

        expect(userFactory.mapPOJOMap).toHaveBeenCalledWith(roomPOJO["users"]);
        expect(room.users).toBe(mockMappedUsers);
      }
    ));

    it("maps the chat room messages payload by delegating to the RoomMessageFactory",
      inject([RoomFactory, RoomMessageFactory], (roomFactory: RoomFactory, roomMessageFactory: RoomMessageFactory) => {
        let mockMappedMessages: Array<RoomMessage> = Array<RoomMessage>();
        spyOn(roomMessageFactory, "mapPOJOList").and.returnValue(mockMappedMessages);

        let room: Room = roomFactory.fromPOJO(roomPOJO);

        expect(roomMessageFactory.mapPOJOList).toHaveBeenCalledWith(roomPOJO['messages']);
        expect(room.messages).toEqual(mockMappedMessages);
      }
    ));

    it("maps the room name",
      inject([RoomFactory], (roomFactory: RoomFactory) => {
        let room: Room = roomFactory.fromPOJO(roomPOJO);
        expect(room.name).toEqual(roomPOJO['name']);
      }
    ));

    it("maps the max number of users allowed in the room",
      inject([RoomFactory], (roomFactory: RoomFactory) => {
        let room: Room = roomFactory.fromPOJO(roomPOJO);
        expect(room.maxNumberOfUsers).toEqual(roomPOJO['maxNumberOfUsers']);
      }
    ));
  });

  describe('#fromPOJOMapToList', () => {
    let roomOne: object, roomTwo: object, roomThree: object;

    beforeEach(() => {
      roomOne = {
        name: "roomOne",
        maxNumberOfUsers: 5,
        messages: new Array<Object>(),
        users: {}
      };

      roomTwo = {
        name: "roomTwo",
        maxNumberOfUsers: 5,
        messages: new Array<Object>(),
        users: {}
      };

      roomThree = {
        name: "roomThree",
        maxNumberOfUsers: 5,
        messages: new Array<Object>(),
        users: {}
      };
    });

    it("delegates to the roomFactory#fromPOJO",
      inject([RoomFactory], (roomFactory: RoomFactory) => {
        let mockRoomOne: Room = new Room({});
        let mockRoomTwo: Room = new Room({});
        let mockRoomThree: Room = new Room({});
        spyOn(roomFactory, "fromPOJO").and.returnValues(mockRoomOne, mockRoomTwo, mockRoomThree);

        let rooms: Array<Room> = roomFactory.fromPOJOMapToList({ roomOne, roomTwo, roomThree });

        let fromPOJOSpy: any = roomFactory.fromPOJO;
        expect(fromPOJOSpy.calls.allArgs().length).toBe(3);

        expect(rooms.length).toBe(3);
        expect(rooms).toContain(mockRoomOne);
        expect(rooms).toContain(mockRoomTwo);
        expect(rooms).toContain(mockRoomThree);
      }
    ));

    it("sorts the rooms by name",
      inject([RoomFactory], (roomFactory: RoomFactory) => {
        let rooms: Array<Room> = roomFactory.fromPOJOMapToList({ roomOne, roomTwo, roomThree });
        expect(rooms[0].name).toEqual("roomOne");
        expect(rooms[1].name).toEqual("roomThree");
        expect(rooms[2].name).toEqual("roomTwo");
      }
    ));
  });
});
