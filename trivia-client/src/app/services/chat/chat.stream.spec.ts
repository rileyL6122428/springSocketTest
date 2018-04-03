import { TestBed, inject } from '@angular/core/testing';

import { ChatStream } from './chat.stream';

describe('ChatService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ChatStream]
    });
  });

  it('should be created', inject([ChatStream], (service: ChatStream) => {
    expect(service).toBeTruthy();
  }));
});
