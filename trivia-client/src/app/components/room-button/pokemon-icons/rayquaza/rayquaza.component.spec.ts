import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RayquazaComponent } from './rayquaza.component';

describe('RayquazaComponent', () => {
  let component: RayquazaComponent;
  let fixture: ComponentFixture<RayquazaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RayquazaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RayquazaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
