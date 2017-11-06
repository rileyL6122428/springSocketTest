import { TestBed, inject } from '@angular/core/testing';
import { RoomFactory } from './room.factory';

describe('RoomFactory', () => {

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ RoomFactory ]
    });
  });

  describe('#fromPOJO', () => {
    xit("returns a room with mapped values",
      inject([RoomFactory], (roomFactory: RoomFactory) => {

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
