import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BlazikenComponent } from './blaziken.component';

describe('BlazikenComponent', () => {
  let component: BlazikenComponent;
  let fixture: ComponentFixture<BlazikenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BlazikenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BlazikenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
