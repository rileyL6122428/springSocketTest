import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GarchompComponent } from './garchomp.component';

describe('GarchompComponent', () => {
  let component: GarchompComponent;
  let fixture: ComponentFixture<GarchompComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GarchompComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GarchompComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
