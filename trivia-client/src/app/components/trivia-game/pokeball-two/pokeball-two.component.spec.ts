import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PokeballTwoComponent } from './pokeball-two.component';

describe('PokeballTwoComponent', () => {
  let component: PokeballTwoComponent;
  let fixture: ComponentFixture<PokeballTwoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PokeballTwoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PokeballTwoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
