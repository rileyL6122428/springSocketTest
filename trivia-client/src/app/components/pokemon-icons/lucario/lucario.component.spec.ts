import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LucarioComponent } from './lucario.component';

describe('LucarioComponent', () => {
  let component: LucarioComponent;
  let fixture: ComponentFixture<LucarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LucarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LucarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
