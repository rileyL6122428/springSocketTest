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
    it("fetches the user from the userService",
      async(inject([MatchmakingService], (matchmakingService) => {
        spyOn(matchmakingService, "getMatchmakingStats").and.returnValue(stubableObservable());
        spyOn(userService, "getUser").and.returnValue(stubableObservable());
        _initializeMatchmakingComponent();
        expect(userService.getUser).toHaveBeenCalled();
    })));

    it("shows the username of the user returned from the userService", async(
      inject([MatchmakingService], (matchmakingService) => {
      spyOn(matchmakingService, "getMatchmakingStats").and.returnValue(stubableObservable());
      let user = new User({ name: "TEST_USER_NAME" });
      spyOn(userService, "getUser").and.returnValue(Observable.create(
        (observer: Observer<User>) => observer.next(user)
      ));

      _initializeMatchmakingComponent();

      let usernameElement = fixture.debugElement.query(By.css("#welcome-user")).nativeElement;
      expect(usernameElement.innerText).toEqual(`Welcome ${user.name}`);
    })));

    it("fetches matchmaking stats from the matchmakingService",
      async(inject([MatchmakingService], (matchmakingService) => {
        spyOn(userService, "getUser").and.returnValue(stubableObservable());
        spyOn(matchmakingService, 'getMatchmakingStats').and.returnValue(stubableObservable());
        _initializeMatchmakingComponent();
        expect(matchmakingService.getMatchmakingStats).toHaveBeenCalled();
      }
    )));

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

      it("shows the number of unplaced users returned from the matchmakingService",
        async(inject([MatchmakingService], (matchmakingService) => {
          spyOn(userService, "getUser").and.returnValue(stubableObservable());
          let matchmakingStats = new MatchmakingStats({ unplacedUserTotal: 2, rooms: [] });
          spyOn(matchmakingService, "getMatchmakingStats").and.returnValue(Observable.create(
            (observer: Observer<MatchmakingStats>) => observer.next(matchmakingStats)
          ));

          _initializeMatchmakingComponent();

          let unplacedUserElement = fixture.debugElement.query(By.css("#unplaced-user-total")).nativeElement;
          expect(unplacedUserElement.innerText).toEqual(`${matchmakingStats.unplacedUserTotal} unplaced users`);
      })));

      it("shows the name, number of users, and the max number of users for each room returned from the matchmakingService",
        async(inject([MatchmakingService], (matchmakingService) => {
          spyOn(userService, "getUser").and.returnValue(stubableObservable());
          spyOn(matchmakingService, "getMatchmakingStats").and.returnValue(Observable.create(
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
      }))
    )});
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
