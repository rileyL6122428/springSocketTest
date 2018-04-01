import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfOakComponent } from './prof-oak.component';

describe('ProfOakComponent', () => {
  let component: ProfOakComponent;
  let fixture: ComponentFixture<ProfOakComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfOakComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfOakComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
