import { TestBed, inject } from '@angular/core/testing';

import { RoomStore } from './room.store';

describe('RoomStore', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RoomStore]
    });
  });

  it('should be created', inject([RoomStore], (service: RoomStore) => {
    expect(service).toBeTruthy();
  }));
});
