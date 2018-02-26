import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PikachuComponent } from './pikachu.component';

describe('PikachuComponent', () => {
  let component: PikachuComponent;
  let fixture: ComponentFixture<PikachuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PikachuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PikachuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
