import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatchmakingComponent } from './matchmaking.component';
import { UserService } from '../services/user.service';
import { Observable } from 'rxjs/observable';
import { Observer } from 'rxjs/observer';
import { User } from '../domain/user/user';
import { By } from '@angular/platform-browser';
import { stubableObservable, stubableSubscription } from '../test-utils/mocks';
import { ServicesModule } from '../services/service.module';

describe('MatchmakingComponent', () => {

  let matchmakingComponent: MatchmakingComponent;
  let fixture: ComponentFixture<MatchmakingComponent>;
  let userService: UserService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ ServicesModule ],
      declarations: [ MatchmakingComponent ],
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
      spyOn(userService, "getUser").and.returnValue(stubableObservable());
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
    it("should remove the getUser subscription", () => {
      let mockSubscription = stubableSubscription(),
          mockObservable = stubableObservable();

      spyOn(mockSubscription, 'unsubscribe');
      spyOn(mockObservable, 'subscribe').and.returnValue(mockSubscription);
      spyOn(userService, "getUser").and.returnValue(mockObservable);

      _initializeMatchmakingComponent();
      matchmakingComponent.ngOnDestroy();

      expect(mockSubscription.unsubscribe).toHaveBeenCalled();
    });
  });

  function _initializeMatchmakingComponent(): void {
    fixture = TestBed.createComponent(MatchmakingComponent);
    matchmakingComponent = fixture.componentInstance;
    fixture.detectChanges();
  }
});
