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
        sender: { name: "EXAMPLE_SENDER_NAME" },
        timestamp: new Date()
      };

      let message: RoomMessage = roomMessageFactory.mapPOJO(messagePOJO);

      expect(message).toEqual(jasmine.any(RoomMessage));
      expect(message.body).toEqual(messagePOJO['body']);
      expect(message.senderName).toEqual(messagePOJO['senderName']);
      expect(message.timestamp).toEqual(messagePOJO['timestamp']);
    }));
  });

  describe('#mapPOJOList', () => {
    it("returns a list of mapped POJOs converted to RoomMessages",
      inject([RoomMessageFactory], (roomMessageFactory: RoomMessageFactory) => {
        let firstMessagePOJO = { body: "BODY1", sender: { name: "SENDER1" }, timestamp: new Date() };
        let secondMessagePOJO = { body: "BODY2", sender: { name: "SENDER2" }, timestamp: new Date() };
        let thirdMessagePOJO = { body: "BODY3", sender: { name: "SENDER3" }, timestamp: new Date() };
        let messagePOJOs: Array<Object> = [ firstMessagePOJO, secondMessagePOJO, thirdMessagePOJO ];

        let messages: Array<RoomMessage> = roomMessageFactory.mapPOJOList(messagePOJOs);

        expect(messages.length).toBe(3);
        messagePOJOs.forEach((messagePOJO, index) => {
          let message = messages[index];
          expect(message).toEqual(jasmine.any(RoomMessage));
          expect(message.body).toEqual(messagePOJO['body']);
          expect(message.senderName).toEqual(messagePOJO['senderName']);
          expect(message.timestamp).toEqual(messagePOJO['timestamp']);
        });
      }
    ));
  });
});
