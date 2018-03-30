import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TriviaUserComponent } from './trivia-user.component';

describe('TriviaUserComponent', () => {
  let component: TriviaUserComponent;
  let fixture: ComponentFixture<TriviaUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TriviaUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TriviaUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
