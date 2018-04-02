import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PokemonRoomIconComponent } from './pokemon-room-icon.component';

describe('PokemonRoomIconComponent', () => {
  let component: PokemonRoomIconComponent;
  let fixture: ComponentFixture<PokemonRoomIconComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PokemonRoomIconComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PokemonRoomIconComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
