import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatchmakingComponent } from './matchmaking.component';
import { UserService } from '../services/user.service';
import { Observable } from 'rxjs/observable';
import { Observer } from 'rxjs/observer';
import { User } from '../domain/user/User';
import { By } from '@angular/platform-browser';


class UserServiceMock {
  getUser(): Observable<User> { return { subscribe: () => {} } as Observable<User>; }
}

describe('MatchmakingComponent', () => {

  let matchmakingComponent: MatchmakingComponent;
  let fixture: ComponentFixture<MatchmakingComponent>;
  let userService: UserService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ MatchmakingComponent ],
      providers: [
        { provide: UserService, useClass: UserServiceMock }
      ]
    })
    .compileComponents();

    userService = TestBed.get(UserService);
  });

  it('should create', () => {
    _initializeMatchmakingComponent();
    expect(matchmakingComponent).toBeTruthy();
  });

  describe("#onInit", () => {
    it("fetches the user from the userService", () => {
      let userService = TestBed.get(UserService);
      spyOn(userService, "getUser").and.returnValue({ subscribe: () => {} });
      _initializeMatchmakingComponent();
      expect(userService.getUser).toHaveBeenCalled();
    });

    it("shows the username of the user returned from the userService", () => {
      let user = new User({ name: "TEST_USER_NAME" });
      spyOn(userService, "getUser").and.returnValue(Observable.create(
        (observer: Observer<User>) => observer.next(user)
      ));

      _initializeMatchmakingComponent();

      let usernameElement = fixture.debugElement.query(By.css("#welcome-user")).nativeElement;
      expect(usernameElement.innerText).toEqual(`Welcome ${user.name}`);
    });
  });

  describe("#onDestroy", () => {
    xit("should remove its subscriptions", () => {

    });
  });

  function _initializeMatchmakingComponent(): void {
    fixture = TestBed.createComponent(MatchmakingComponent);
    matchmakingComponent = fixture.componentInstance;
    fixture.detectChanges();
  }
});
