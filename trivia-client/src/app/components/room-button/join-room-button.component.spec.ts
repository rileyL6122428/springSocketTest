import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JoinRoomButtonComponent } from './join-room-button.component';

describe('JoinRoomButtonComponent', () => {
  let component: JoinRoomButtonComponent;
  let fixture: ComponentFixture<JoinRoomButtonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JoinRoomButtonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JoinRoomButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
