import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuarterArcComponent } from './quarter-arc.component';

describe('QuarterArcComponent', () => {
  let component: QuarterArcComponent;
  let fixture: ComponentFixture<QuarterArcComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuarterArcComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuarterArcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
