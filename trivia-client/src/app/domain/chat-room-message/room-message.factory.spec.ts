import { TestBed, inject } from '@angular/core/testing';
import { RoomMessageFactory } from './room-message.factory';
import { RoomMessage } from './room-message';

describe('RoomMessageFactory', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ RoomMessageFactory ]
    });
  });

  describe('#mapPOJO', () => {
    it("maps a POJO to a an instance of the message class", inject([RoomMessageFactory], (roomMessageFactory) => {
      let messagePOJO: Object = {
        body: "EXAMPLE_BODY",
        senderName: "EXAMPLE_SENDER_NAME",
        timestamp: new Date()
      };

      let message: RoomMessage = roomMessageFactory.mapPOJO(messagePOJO);

      expect(message.body).toEqual(messagePOJO['body']);
      expect(message.senderName).toEqual(messagePOJO['senderName']);
      expect(message.timestamp).toEqual(messagePOJO['timestamp']);
    }));
  });
});
