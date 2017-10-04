import { Component, Inject } from '@angular/core';
// import { NgClass } from '@angular/common';
import { Http } from '@angular/http';
import { Room } from '../domain/Room';
import { Message } from '@stomp/stompjs';
import { StompService, StompState, StompConfig } from '@stomp/ng2-stompjs';
import { Subscription } from 'rxjs/Subscription';

import { StompServiceFacade } from '../stomp-module/services/stomp.service.facade';

@Component({
  template: `
    <section id="rooms" *ngIf="!selectedRoom">
      <p>{{unplacedUsersCount}} people waiting</p>

      <h4>Rooms</h4>
      <ul *ngIf="!selectedRoom">
        <li *ngFor="let room of rooms" [ngClass]="'sup'">
          <p>{{room.getName()}}<p>
          <p>Number of participants: {{room.getTotalNumberOfUsers()}}</p>
          <button (click)="joinChatRoom(room.getName())">Join</button>
        </li>
      </ul>
    </section>

    <section id="selected-room" *ngIf="!!selectedRoom">
      <h4>Chat Room: {{selectedRoom.getName()}}</h4>
      <p>Number of participants: {{selectedRoom.getTotalNumberOfUsers()}}</p>
      <button (click)="leaveChatRoom()">Leave now</button>
    </section>
  `
})
export class MatchmakingComponent {

  private unplacedUsersCount: number;
  private rooms: Array<Room>;
  private selectedRoom: Room;

  constructor(
    @Inject(StompServiceFacade) private stompService: StompServiceFacade,
    @Inject(Http) private http: Http
  ) {

    this.stompService.subscribe("/topic/matchmaking", (messageBody: Object) => {
      this.setRooms(messageBody);
      this.unplacedUsersCount = messageBody['userTotal'] - this.placedUserTotal();
    });

    this.stompService.subscribe("/user/queue/matchmaking", (messageBody: Object) => {
      debugger
      console.log(messageBody);
      if(messageBody['requestSuccessful']) {
        this.selectedRoom = Room.fromPOJO(messageBody['room']);
      }
    });
    debugger
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
    // this.stompService.publish("/app/matchmaking/join-room", { roomName });
    debugger
    this.http.post("/test", { room: this.selectedRoom }, {})
      .map((response) => {
        debugger
        console.log(response);

        return response.json();
      })
      .subscribe((response) => {
        debugger
        console.log(response);
      });
  }

  private leaveChatRoom(): void {
    debugger
    this.stompService.publish("/app/matchmaking/leave-room", { room: this.selectedRoom });
    this.selectedRoom = null;
  }

}
