import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PokemonAnswerComponent } from './pokemon-answer.component';

describe('PokemonAnswerComponent', () => {
  let component: PokemonAnswerComponent;
  let fixture: ComponentFixture<PokemonAnswerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PokemonAnswerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PokemonAnswerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
