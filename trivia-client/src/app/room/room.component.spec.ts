import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomComponent } from './room.component';

describe('RoomComponent', () => {
  let component: RoomComponent;
  let fixture: ComponentFixture<RoomComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoomComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe("#onInit", () => {
    xit("fetches the room from the roomService");
    xit("subscribes to the room's STOMP Broker endpoint through the roomService");
  });

  describe("exposed state", () => {
    xit("exposes the room retrieved from the getRoom subscription");
    xit("exposes the room retrieved from the roomSTOMPSubscription");
  });

  describe("#onDestroy", () => {
    xit("removes the getRoom subscription");
    xit("removes the roomSTOMP subscription");
  });
});
