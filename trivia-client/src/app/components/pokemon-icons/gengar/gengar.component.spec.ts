import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GengarComponent } from './gengar.component';

describe('GengarComponent', () => {
  let component: GengarComponent;
  let fixture: ComponentFixture<GengarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GengarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GengarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
