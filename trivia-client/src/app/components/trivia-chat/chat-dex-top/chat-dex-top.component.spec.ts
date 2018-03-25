import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChatDexTopComponent } from './chat-dex-top.component';

describe('ChatDexTopComponent', () => {
  let component: ChatDexTopComponent;
  let fixture: ComponentFixture<ChatDexTopComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChatDexTopComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChatDexTopComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
