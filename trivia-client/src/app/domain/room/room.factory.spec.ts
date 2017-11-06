import { TestBed, inject } from '@angular/core/testing';
import { RoomFactory } from './room.factory';
import { RoomMessageFactory } from '../chat-room-message/room-message.factory';
import { RoomMessage } from '../chat-room-message/room-message';
import { User } from '../user/user';
import { Room } from './room';

describe('RoomFactory', () => {

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        RoomFactory,
        RoomMessageFactory
      ]
    });
  });

  describe('#fromPOJO', () => {
    it("returns a room with mapped values",
      inject([RoomFactory], (roomFactory: RoomFactory) => {
        let roomPOJO: Object = {
          name: "EXAMPLE_NAME",
          maxNumberOfUsers: 5,
          messages: new Array<Object>(),
          users: new Array<Object>()
        };

        let room: Room = roomFactory.fromPOJO(roomPOJO);

        expect(room.name).toEqual(roomPOJO['name']);
        expect(room.maxNumberOfUsers).toEqual(roomPOJO['maxNumberOfUsers']);
        expect(room.messages).toEqual(roomPOJO['messages']);
        expect(room.users).toEqual(roomPOJO['users']);
      }
    ));
  });

  describe('#fromPOJOMapToList', () => {
    xit("should convert the POJO map to an ordered list of rooms",
      inject([RoomFactory], (roomFactory: RoomFactory) => {

      }
    ));
  });

});
