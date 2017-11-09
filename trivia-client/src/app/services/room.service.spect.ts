import { TestBed, inject } from '@angular/core/testing';
import { HttpModule, XHRBackend, ResponseOptions, Connection, Response } from '@angular/http';
import { MockBackend, MockConnection } from '@angular/http/testing';
import { RoomService } from './room.service';
import { RoomFactory } from '../domain/room/room.factory';

describe('RoomService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpModule ],
      providers: [
        RoomFactory,
        RoomService,
        { provide: XHRBackend, useClass: MockBackend }
      ]
    });
  });

  describe('#getRoom', () => {

  });
});
