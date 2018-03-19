import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TriviaChatComponent } from './trivia-chat.component';

describe('TriviaChatComponent', () => {
  let component: TriviaChatComponent;
  let fixture: ComponentFixture<TriviaChatComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TriviaChatComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TriviaChatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
