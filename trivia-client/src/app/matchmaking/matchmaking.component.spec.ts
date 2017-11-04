import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MatchmakingComponent } from './matchmaking.matchmakingComponent';
import { HttpModule } from '@angular/http'
import { UserService } from '../services/user.service';
import { Subscription } from 'rxjs/subscription';

class UserServiceMock {
  getUser(): Subscription { return null; }
}

describe('MatchmakingComponent', () => {
  let matchmakingComponent: MatchmakingComponent;
  let fixture: ComponentFixture<MatchmakingComponent>;
  let userService: UserService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpModule ],
      declarations: [ MatchmakingComponent ],
      providers: [
        { provide: UserService, useClass: UserServiceMock }
      ]
    })
    .compileComponents();

    userService = TestBed.get(UserService);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MatchmakingComponent);
    matchmakingComponent = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(matchmakingComponent).toBeTruthy();
  });

  describe("#onInit", () => {
    it("it should call userService.getUser", () => {
      spyOn(userService, "getUser");
      matchmakingComponent.ngOnInit();
      expect(userService.getUser).toHaveBeenCalled();
    });
  });
});
