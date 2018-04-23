import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PokeballComponent } from './pokeball.component';

describe('PokeballTwoComponent', () => {
  let component: PokeballComponent;
  let fixture: ComponentFixture<PokeballComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PokeballComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PokeballComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
