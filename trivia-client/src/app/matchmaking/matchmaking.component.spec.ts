import { ComponentFixture, TestBed, inject } from '@angular/core/testing';
import { MatchmakingComponent } from './matchmaking.component';
import { UserService } from '../services/user.service';
import { Observable } from 'rxjs/observable';
import { Observer } from 'rxjs/observer';
import { User } from '../domain/user/user';
import { By } from '@angular/platform-browser';
import { stubableObservable, stubableSubscription } from '../test-utils/mocks';
import { ServicesModule } from '../services/service.module';
import { MatchmakingService } from '../services/matchmaking.service';
import { MatchmakingStats } from '../domain/matchmaking/matchmaking-stats';

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

    it("fetches matchmaking stats from the matchmakingService",
      inject([MatchmakingService], (matchmakingService) => {
        spyOn(matchmakingService, 'getMatchmakingStats').and.returnValue(stubableObservable());
        _initializeMatchmakingComponent();
        expect(matchmakingService.getMatchmakingStats).toHaveBeenCalled();
      }
    ));

    it("shows the number of unplaced users returned from the matchmakingService",
      inject([MatchmakingService], (matchmakingService) => {
        let matchmakingStats = new MatchmakingStats({ unplacedUserTotal: 2, rooms: [] });
        spyOn(matchmakingService, "getMatchmakingStats").and.returnValue(Observable.create(
          (observer: Observer<MatchmakingStats>) => observer.next(matchmakingStats)
        ));

        _initializeMatchmakingComponent();

        let unplacedUserElement = fixture.debugElement.query(By.css("#unplaced-user-total")).nativeElement;
        expect(unplacedUserElement.innerText).toEqual(`${matchmakingStats.unplacedUserTotal} unplaced users`);
    }));
  });

  describe("#onDestroy", () => {
    it("removes the getUser subscription", () => {
      let mockSubscription = stubableSubscription(),
          mockObservable = stubableObservable();

      spyOn(mockSubscription, 'unsubscribe');
      spyOn(mockObservable, 'subscribe').and.returnValue(mockSubscription);
      spyOn(userService, "getUser").and.returnValue(mockObservable);

      _initializeMatchmakingComponent();
      matchmakingComponent.ngOnDestroy();

      expect(mockSubscription.unsubscribe).toHaveBeenCalled();
    });

    it("removes the getStats subscription",
      inject([MatchmakingService], (matchmakingService: MatchmakingService) => {
        let mockSubscription = stubableSubscription(),
            mockObservable = stubableObservable();

        spyOn(mockSubscription, 'unsubscribe');
        spyOn(mockObservable, 'subscribe').and.returnValue(mockSubscription);
        spyOn(matchmakingService, "getMatchmakingStats").and.returnValue(mockObservable);

        _initializeMatchmakingComponent();
        matchmakingComponent.ngOnDestroy();

        expect(mockSubscription.unsubscribe).toHaveBeenCalled();
      }
    ));
  });

  function _initializeMatchmakingComponent(): void {
    fixture = TestBed.createComponent(MatchmakingComponent);
    matchmakingComponent = fixture.componentInstance;
    fixture.detectChanges();
  }
});
