import { Component, Inject, OnInit, OnDestroy } from '@angular/core';
import { Http } from '@angular/http';
import { Router } from '@angular/router';
import { Room } from '../domain/Room';
import { Message } from '@stomp/stompjs';
import { Subscription } from 'rxjs/Subscription';
import { StompServiceFacade } from '../stomp-module/services/stomp.service.facade';
import { UserService } from '../services/user.service';
import { User } from '../domain/User';

@Component({
  template: `
    <section id="user-welcome" *ngIf="user">
      <p>Welcome {{user.getName()}}</p>
    </section>

      <section id="unplaced-users">
        <p>{{unplacedUsersCount}} people waiting</p>
      </section>

      <section id="rooms">
        <h4>Rooms</h4>

        <ul>
          <li *ngFor="let room of rooms" [ngClass]="'sup'">
            <p>{{room.getName()}}<p>
            <p>Number of participants: {{room.getTotalNumberOfUsers()}}</p>
            <button (click)="joinChatRoom(room.getName())">Join</button>
          </li>
        </ul>
      </section>
  `
})
export class MatchmakingComponent implements OnInit, OnDestroy {

  private unplacedUsersCount: number;
  private rooms: Array<Room>;
  private user: User;
  private subscriptions: Array<Subscription>;

  constructor(
    @Inject(StompServiceFacade) private stompService: StompServiceFacade,
    @Inject(Http) private http: Http,
    @Inject(Router) private router: Router,
    @Inject(UserService) private userService: UserService
  ) { }

  ngOnInit(): void {
    this.subscriptions = [
      this.getMatchmakingStats(),
      this.getUser(),
      this.subscribeToMatchmaking()
    ];
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach( subscription => subscription.unsubscribe() );
  }

  private subscribeToMatchmaking(): Subscription {
    return this.stompService.subscribe("/topic/matchmaking", (messageBody: Object) => {
      this.setMatchmakingStats(messageBody);
    });
  }

  private getMatchmakingStats(): Subscription {
    return this.http.get("/matchmaking/stats").subscribe((response) => {
      let responseBody: Object =  response.json();
      this.setMatchmakingStats(responseBody);
    });
  }

  private getUser(): Subscription {
    return this.http.get("/user").subscribe((response) => {
      let userPOJO: Object = response.json();
      let user: User = User.fromPOJO(userPOJO);
      this.user = user;
      this.userService.storeUser(user);
    });
  }

  private setMatchmakingStats(responseBody: Object): void {
    this.setRooms(responseBody);
    this.unplacedUsersCount = responseBody['userTotal'] - this.placedUserTotal();
  }

  private setRooms(messageBody: Object): void {
    let receivedRooms: Map<string, Room> = messageBody['rooms'];
    this.rooms = new Array<Room>();

    for(let roomName in receivedRooms) {
      let roomPOJO = receivedRooms[roomName];
      let room: Room = Room.fromPOJO(roomPOJO);

      this.rooms.push(room);
    }
  }

  private placedUserTotal(): number {
    let placedUserTotal = 0;

    this.rooms.forEach((room: Room) => {
      placedUserTotal += room.getTotalNumberOfUsers();
    });

    return placedUserTotal;
  }

  private joinChatRoom(roomName: string): void {
    this.http.post("/join-chat-room", { roomName: roomName }, {})

      .subscribe((response) => {
        if(response.status === 200)
          this.joinRoom(response.json())
        else
          this.showJoinChatFailureModal(response.json());
      });
  }

  private joinRoom(responseBody: Object)  {
    let targetRoom = Room.fromPOJO(responseBody['room']);
    this.router.navigateByUrl('/chat/' + targetRoom.getName());
  }

  private showJoinChatFailureModal(responseBody: Object) {
    console.log(responseBody);
  }

}
