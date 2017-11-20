import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { MatchmakingComponent } from './matchmaking.component';
import { UserService } from '../services/user.service';
import { User } from '../domain/user/user';
import { stubableObservable, stubableSubscription } from '../test-utils/mocks';
import { ServicesModule } from '../services/service.module';
import { MatchmakingService } from '../services/matchmaking.service';
import { MatchmakingStats } from '../domain/matchmaking/matchmaking-stats';
import { Room } from '../domain/room/room';
import { watchForUnsubscription } from '../test-utils/unsubscription-watcher';
import { StompService } from '@stomp/ng2-stompjs';
import { Observable } from 'rxjs/Observable';
import { Observer } from 'rxjs/Observer';
import { Subscription } from 'rxjs/Subscription';
import { RouterModule } from '@angular/router';

describe('MatchmakingComponent', () => {

  let matchmakingComponent: MatchmakingComponent;
  let fixture: ComponentFixture<MatchmakingComponent>;
  let userService: UserService;
  let matchmakingService: MatchmakingService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        ServicesModule,
        RouterModule
      ],
      declarations: [ MatchmakingComponent ],
      providers: [
        { provide: StompService, useValue: {} },
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    userService = TestBed.get(UserService);
    spyOn(userService, "getUser").and.returnValue(stubableObservable());
  });

  beforeEach(() => {
    matchmakingService = TestBed.get(MatchmakingService);
    spyOn(matchmakingService, "getMatchmakingStats").and.returnValue(stubableObservable());
    spyOn(matchmakingService, "subscribeToMatchmaking").and.returnValue(stubableObservable());
  });

  it('should create', () => {
    _initializeMatchmakingComponent();
    expect(matchmakingComponent).toBeTruthy();
  });

  describe("#onInit", () => {
    it("fetches the user from the userService",async(()=> {
        _initializeMatchmakingComponent();
        expect(userService.getUser).toHaveBeenCalled();
    }));

    it("fetches matchmaking stats from the matchmakingService", async(() => {
        _initializeMatchmakingComponent();
        expect(matchmakingService.getMatchmakingStats).toHaveBeenCalled();
    }));

    it("subscribes to matchmaking updates through the matchmakingService", async(() => {
      _initializeMatchmakingComponent();
      expect(matchmakingService.subscribeToMatchmaking).toHaveBeenCalled();
    }));
  });

  describe("#onDestroy", () => {
    it("removes the getUser subscription", () => {
      let mockSubscription = watchForUnsubscription(userService.getUser);
      _initializeMatchmakingComponent();
      matchmakingComponent.ngOnDestroy();
      expect(mockSubscription.unsubscribe).toHaveBeenCalled();
    });

    it("removes the getStats subscription", () => {
      let mockSubscription = watchForUnsubscription(matchmakingService.getMatchmakingStats);
      _initializeMatchmakingComponent();
      matchmakingComponent.ngOnDestroy();
      expect(mockSubscription.unsubscribe).toHaveBeenCalled();
    });

    it("removes the matchmakingStomp subscription", () => {
      let mockSubscription = watchForUnsubscription(matchmakingService.subscribeToMatchmaking);
      _initializeMatchmakingComponent();
      matchmakingComponent.ngOnDestroy();
      expect(mockSubscription.unsubscribe).toHaveBeenCalled();
    });
  });

  describe("username display", () => {
    it("exposes the user returned from the user service", async(() => {
      let user = new User({ name: "TEST_USER_NAME" });
      (userService.getUser as jasmine.Spy).and.returnValue(Observable.create(
        (observer: Observer<User>) => observer.next(user)
      ));

      _initializeMatchmakingComponent();

      expect(matchmakingComponent.user).toBe(user);
    }));
  });

  describe("matchmaking stats display", () => {
    let matchmakingStats: MatchmakingStats;
    beforeEach( () => matchmakingStats = new MatchmakingStats({}) );

    it("exposes the matchmaking stats returned from ", async(() => {
        (matchmakingService.getMatchmakingStats as jasmine.Spy).and.returnValue(Observable.create(
          (observer: Observer<MatchmakingStats>) => observer.next(matchmakingStats)
        ));

        _initializeMatchmakingComponent();

        expect(matchmakingComponent.matchmakingStats).toBe(matchmakingStats);
    }));

    it("exposes stats retrieved from the stomp subscription", async(() => {
      (matchmakingService.subscribeToMatchmaking as jasmine.Spy).and.returnValue(Observable.create(
        (observer: Observer<MatchmakingStats>) => observer.next(matchmakingStats)
      ));

      _initializeMatchmakingComponent();

      expect(matchmakingComponent.matchmakingStats).toBe(matchmakingStats);
    }));
  });

  function _initializeMatchmakingComponent(): void {
    fixture = TestBed.createComponent(MatchmakingComponent);
    matchmakingComponent = fixture.componentInstance;
    fixture.detectChanges();
  }
});
