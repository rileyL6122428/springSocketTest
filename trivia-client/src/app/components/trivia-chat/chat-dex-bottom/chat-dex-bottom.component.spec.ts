import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChatDexBottomComponent } from './chat-dex-bottom.component';

describe('ChatDexBottomComponent', () => {
  let component: ChatDexBottomComponent;
  let fixture: ComponentFixture<ChatDexBottomComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChatDexBottomComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChatDexBottomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
