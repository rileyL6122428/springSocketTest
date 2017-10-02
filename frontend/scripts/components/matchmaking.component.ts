import { Component, Inject } from '@angular/core';
import { Room } from '../domain/Room';
import { Message } from '@stomp/stompjs';
import { StompService, StompState, StompConfig } from '@stomp/ng2-stompjs';
import { Subscription } from 'rxjs/Subscription';

import { StompServiceFacade } from '../stomp-module/services/stomp.service.facade';

@Component({
  template: `
    <section id="rooms">
      <p>{{unplacedUsersCount}} people waiting</p>

      <h4>Rooms</h4>
      <ul>
        <li *ngFor="let room of rooms">
          <p>{{room.getName()}}<p>
          <p>Number of participants: {{room.getTotalNumberOfUsers()}}</p>
          <button (click)="joinChatRoom()">Join</button>
        </li>
      </ul>
    </section>
  `
})
export class MatchmakingComponent {

  private unplacedUsersCount: number;
  private rooms: Array<Room>;

  constructor(@Inject(StompServiceFacade) private stompService: StompServiceFacade) {

    this.stompService.subscribe("/topic/matchmaking-stats", (messageBody: Object) => {
      this.setRooms(messageBody);
      this.unplacedUsersCount = messageBody['userTotal'] - this.placedUserTotal();
    });

    this.stompService.subscribe("/user/queue/matchmaking", (messageBody: Object) => {
      debugger
      console.log(messageBody);
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

  private joinChatRoom(): void {
    debugger
    this.stompService.publish("/app/matchmaking/join-room", { yolo: "yolo-join" });
  }

}
