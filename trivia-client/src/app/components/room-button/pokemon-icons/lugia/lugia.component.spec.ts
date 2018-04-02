import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LugiaComponent } from './lugia.component';

describe('LugiaComponent', () => {
  let component: LugiaComponent;
  let fixture: ComponentFixture<LugiaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LugiaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LugiaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
