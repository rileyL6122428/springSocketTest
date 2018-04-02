import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MewtwoComponent } from './mewtwo.component';

describe('MewtwoComponent', () => {
  let component: MewtwoComponent;
  let fixture: ComponentFixture<MewtwoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MewtwoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MewtwoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
