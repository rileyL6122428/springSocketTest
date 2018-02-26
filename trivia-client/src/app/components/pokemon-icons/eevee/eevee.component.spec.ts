import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EeveeComponent } from './eevee.component';

describe('EeveeComponent', () => {
  let component: EeveeComponent;
  let fixture: ComponentFixture<EeveeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EeveeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EeveeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
