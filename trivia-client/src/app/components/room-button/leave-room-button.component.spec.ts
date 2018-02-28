import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LeaveRoomButtonComponent } from './leave-room-button.component';

describe('LeaveRoomButtonComponent', () => {
  let component: LeaveRoomButtonComponent;
  let fixture: ComponentFixture<LeaveRoomButtonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LeaveRoomButtonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LeaveRoomButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
