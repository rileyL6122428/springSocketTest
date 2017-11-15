import { ComponentFixture, TestBed, inject, async } from '@angular/core/testing';
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
import { Room } from '../domain/room/room';
import { Subscription } from 'rxjs/subscription';
import { watchForUnsubscription } from '../test-utils/unsubscription-watcher';
import { StompService } from '@stomp/ng2-stompjs';

describe('MatchmakingComponent', () => {

  let matchmakingComponent: MatchmakingComponent;
  let fixture: ComponentFixture<MatchmakingComponent>;
  let userService: UserService;
  let matchmakingService: MatchmakingService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ ServicesModule ],
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
  });

  describe("username display", () => {
    it("shows the username of the user returned from the userService", async(() => {
      let user = new User({ name: "TEST_USER_NAME" });
      (userService.getUser as jasmine.Spy).and.returnValue(Observable.create(
        (observer: Observer<User>) => observer.next(user)
      ));

      _initializeMatchmakingComponent();

      let usernameElement = fixture.debugElement.query(By.css("#welcome-user")).nativeElement;
      expect(usernameElement.innerText).toEqual(`Welcome ${user.name}`);
    }));
  });

  describe("matchmaking stats display", () => {
    let matchmakingStats: MatchmakingStats;

    beforeEach(() => {
      matchmakingStats = new MatchmakingStats({
        unplacedUserTotal: 2,
        rooms: [
          new Room({
            name: "ROOM_ONE",
            maxNumberOfUsers: 3,
            users: new Map<string, User>()
                    .set("tim", new User({ name: "tim" }))
                    .set("tom", new User({ name: "tom" }))
          }),
          new Room({
            name: "ROOM_TWO",
            maxNumberOfUsers: 5,
            users: new Map<string, User>()
                    .set("tam", new User({ name: "tam" }))
          })
        ]
      });
    });

    it("shows the number of unplaced users returned from the matchmakingService", async(() => {
        (matchmakingService.getMatchmakingStats as jasmine.Spy).and.returnValue(Observable.create(
          (observer: Observer<MatchmakingStats>) => observer.next(matchmakingStats)
        ));

        _initializeMatchmakingComponent();

        let unplacedUserElement = fixture.debugElement.query(By.css("#unplaced-user-total")).nativeElement;
        expect(unplacedUserElement.innerText).toEqual(`${matchmakingStats.unplacedUserTotal} unplaced users`);
    }));

    it("shows the name, number of users, and the max number of users for each room returned from the matchmakingService", async(() => {
        (matchmakingService.getMatchmakingStats as jasmine.Spy).and.returnValue(Observable.create(
          (observer: Observer<MatchmakingStats>) => observer.next(matchmakingStats)
        ));

        _initializeMatchmakingComponent();

        let roomsListElement = fixture.debugElement.query(By.css("#rooms")).nativeElement;
        expect(roomsListElement.children.length).toBe(2);

        let roomOneListItem: HTMLElement = roomsListElement.children[0];
        expect(roomOneListItem.innerHTML).toContain(`ROOM_ONE`);
        expect(roomOneListItem.innerHTML).toContain(`2/3`);

        let roomTwoElement: HTMLElement = roomsListElement.children[1];
        expect(roomTwoElement.innerHTML).toContain(`ROOM_TWO`);
        expect(roomTwoElement.innerHTML).toContain(`1/5`);
    }));
  });

  function _initializeMatchmakingComponent(): void {
    fixture = TestBed.createComponent(MatchmakingComponent);
    matchmakingComponent = fixture.componentInstance;
    fixture.detectChanges();
  }
});
