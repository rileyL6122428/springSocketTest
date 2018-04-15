import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CharmanderComponent } from './charmander.component';

describe('CharmanderComponent', () => {
  let component: CharmanderComponent;
  let fixture: ComponentFixture<CharmanderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CharmanderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CharmanderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
