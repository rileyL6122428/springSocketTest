import { Component, Inject, OnInit, OnDestroy } from '@angular/core';
import { Http } from '@angular/http';
import { Router } from '@angular/router';
import { Room } from '../domain/Room';
import { Message } from '@stomp/stompjs';
import { Subscription } from 'rxjs/Subscription';
import { StompServiceFacade } from '../stomp-module/services/stomp.service.facade';
import { CookieService } from 'angular2-cookie/services/cookies.service'

@Component({
  template: `
      <section id="unplaced-users">
        <p>{{unplacedUsersCount}} people waiting</p>
      </section>

      <section id="rooms">
        <h4>Rooms</h4>

        <ul *ngIf="!selectedRoom">
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
  private selectedRoom: Room;
  private subscriptions: Array<Subscription>;

  constructor(
    @Inject(StompServiceFacade) private stompService: StompServiceFacade,
    @Inject(Http) private http: Http,
    @Inject(Router) private router: Router,
    @Inject(CookieService) private cookieService: CookieService
  ) {
    this.subscriptions = new Array<Subscription>();
  }

  ngOnInit(): void {
    let matchmakingSubscription = this.stompService.subscribe("/topic/matchmaking", (messageBody: Object) => {
      this.setRooms(messageBody);
      this.unplacedUsersCount = messageBody['userTotal'] - this.placedUserTotal();
    });
    this.stompService.publish("/app/matchmaking/enter", {});

    this.subscriptions.push(matchmakingSubscription);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach( subscription => subscription.unsubscribe());
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
    debugger
    this.http.post("/join-chat-room", { roomName: roomName }, {})

      .subscribe((response) => {
        if(response.status === 200)
          this.joinRoom(response.json());
        else
          this.showJoinChatFailureModal(response.json());
      });
  }

  private joinRoom(response: Object)  {
    console.log(response);
    this.router.navigateByUrl('/chat');
  }

  private showJoinChatFailureModal(response: Object) {
    console.log(response);
  }

}
