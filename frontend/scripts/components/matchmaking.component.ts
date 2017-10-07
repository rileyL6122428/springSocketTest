import { Component, Inject, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { Router } from '@angular/router';
import { Room } from '../domain/Room';
import { Message } from '@stomp/stompjs';
import { Subscription } from 'rxjs/Subscription';
import { StompServiceFacade } from '../stomp-module/services/stomp.service.facade';

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
export class MatchmakingComponent implements OnInit {

  private unplacedUsersCount: number;
  private rooms: Array<Room>;
  private selectedRoom: Room;

  constructor(
    @Inject(StompServiceFacade) private stompService: StompServiceFacade,
    @Inject(Http) private http: Http,
    @Inject(Router) private router: Router
  ) { }

  ngOnInit() {
    this.stompService.subscribe("/topic/matchmaking", (messageBody: Object) => {
      this.setRooms(messageBody);
      this.unplacedUsersCount = messageBody['userTotal'] - this.placedUserTotal();
    });

    this.stompService.publish("/app/matchmaking/enter", { yolo: "yolo-enter" });
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
    this.http.post("/join-chat-room", { room: this.selectedRoom }, {})

      .subscribe((response) => {
        debugger
        if(response.status === 200)
          this.joinRoom(response.json());
        else
          this.showJoinChatFailureModal(response.json());
      });
  }

  private joinRoom(response: Object)  {
    debugger
    console.log(response);
    this.router.navigateByUrl('/chat');
  }

  private showJoinChatFailureModal(response: Object) {
    debugger
    console.log(response);
  }

}
