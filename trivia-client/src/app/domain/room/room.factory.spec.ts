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
    it("returns a room with mapped values",
      inject([RoomFactory], (roomFactory: RoomFactory) => {
        let roomPOJO: Object = {
          name: "EXAMPLE_NAME",
          maxNumberOfUsers: 5,
          messages: new Array<Object>(),
          users: new Array<Object>()
        };

        let room: Room = roomFactory.fromPOJO(roomPOJO);

        expectRoomPOJOToEqualRoom(room, roomPOJO);
      }
    ));
  });

  describe('#fromPOJOMapToList', () => {
    it("should convert the POJO map to an ordered list of rooms",
      inject([RoomFactory], (roomFactory: RoomFactory) => {
        let roomPOJOs: Map<string, Object> = new Map<string, Object>();
        roomPOJOs.set('cba', {
          name: "cba",
          maxNumberOfUsers: 5,
          messages: new Array<Object>(),
          users: new Array<Object>()
        });
        roomPOJOs.set('bac', {
          name: "bac",
          maxNumberOfUsers: 5,
          messages: new Array<Object>(),
          users: new Array<Object>()
        });
        roomPOJOs.set('abc', {
          name: "abc",
          maxNumberOfUsers: 5,
          messages: new Array<Object>(),
          users: new Array<Object>()
        });
        
        let rooms: Array<Room> = roomFactory.fromPOJOMapToList(roomPOJOs);

        expect(rooms.length).toEqual(3);
        expectRoomPOJOToEqualRoom(rooms[0], roomPOJOs.get('abc'));
        expectRoomPOJOToEqualRoom(rooms[1], roomPOJOs.get('bac'));
        expectRoomPOJOToEqualRoom(rooms[2], roomPOJOs.get('cba'));
      }
    ));

  });

  function expectRoomPOJOToEqualRoom(room: Room, roomPOJO: Object) {
    expect(room.name).toEqual(roomPOJO['name']);
    expect(room.maxNumberOfUsers).toEqual(roomPOJO['maxNumberOfUsers']);
    expect(room.messages).toEqual(roomPOJO['messages']);
    expect(room.users).toEqual(roomPOJO['users']);
  }

});
