import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BulbasuarComponent } from './bulbasuar.component';

describe('BulbasuarComponent', () => {
  let component: BulbasuarComponent;
  let fixture: ComponentFixture<BulbasuarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BulbasuarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BulbasuarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
