import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BulbasaurComponent } from './bulbasuar.component';

describe('BulbasuarComponent', () => {
  let component: BulbasaurComponent;
  let fixture: ComponentFixture<BulbasaurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BulbasaurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BulbasaurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
