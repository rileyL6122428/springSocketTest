import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomButtonComponent } from './join-room-button.component';

describe('JoinRoomButtonComponent', () => {
  let component: RoomButtonComponent;
  let fixture: ComponentFixture<RoomButtonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoomButtonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoomButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
