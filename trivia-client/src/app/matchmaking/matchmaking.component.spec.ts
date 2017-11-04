import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MatchmakingComponent } from './matchmaking.component';
import { HttpModule } from '@angular/http'
import { DebugElement } from '@angular/core';
import { UserService } from '../services/user.service';
import { Observable } from 'rxjs/observable';
import { Observer } from 'rxjs/observer';
import { User } from '../domain/user/User';
import { Subscription } from 'rxjs/subscription';
import { By } from '@angular/platform-browser';

class UserServiceMock {
  getUser(): Subscription { return null; }
}

describe('MatchmakingComponent', () => {

  let matchmakingComponent: MatchmakingComponent;
  let fixture: ComponentFixture<MatchmakingComponent>;
  let userService: UserService;
  let getUserObserver: Observer<User>;

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

  beforeEach(() => {
    _mockUserService();
    _initializeMatchmakingComponent();
  });

  it('should create', () => {
    expect(matchmakingComponent).toBeTruthy();
  });

  describe("#onInit", () => {
    it("it should call userService.getUser", () => {
      expect(userService.getUser).toHaveBeenCalled();
    });

    it("it should show the username of the user returned from userService.getUser", () => {
      let user: User = new User({ name: "TEST_USER_NAME" });
      getUserObserver.next(user);
      //
      fixture.detectChanges();
      let usernameElement = fixture.debugElement.query(By.css("#welcome-user")).nativeElement;
      expect(usernameElement.innerText).toEqual("Welcome TEST_USER_NAME");
    });
  });

  function _mockUserService() {
    spyOn(userService, "getUser").and.returnValue(
      new Observable<User>((observer: Observer<User>) => {
        getUserObserver = observer;
      })
    );
  }

  function _initializeMatchmakingComponent() {
    fixture = TestBed.createComponent(MatchmakingComponent);
    matchmakingComponent = fixture.componentInstance;
    fixture.detectChanges();
  }
});
