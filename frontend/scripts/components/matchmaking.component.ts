import { Component, Inject } from '@angular/core';
import { Room } from '../domain/Room';
import { Message } from '@stomp/stompjs';
import { StompService, StompState, StompConfig } from '@stomp/ng2-stompjs';
import { Subscription } from 'rxjs/Subscription';

@Component({
  template: `
    <section id="connection-buttons">
      <button id="connect-button">Connect</button>
      <button id="disconnect-button">Disconnect</button>
    </section>

    <section id="rooms" *ngIf="connectedToWebSockets">
      <h3>Connected</h3>

      <h4>Rooms</h4>
      <ul>
        <li *ngFor="let room of rooms">
          {{room.getName()}}
        </li>
      </ul>
    </section>
  `
})
export class MatchmakingComponent {

  private connectedToWebSockets: boolean;
  private rooms: Array<Room>;

  constructor() {
    console.log("HELLO")

    let stompService:StompService = new StompService({
      url: 'ws://localhost:8090/matchmaking/websocket',
      headers: {
        username: "user1"
      },
      heartbeat_in: 0,
      heartbeat_out: 20000,
      reconnect_delay: 5000,
      debug: true
    });

    var subscription:Subscription = stompService.subscribe('/topic/matchmaking-stats')
    .map((message: Message) => {
      return message.body;
    }).subscribe((messageBody: string) => {
      console.log(`Received: ${messageBody}`);
    });
    debugger

    stompService.publish('/app/matchmaking', JSON.stringify({ roomName: "sup" }));



    // this.rooms =  [
    //   Room.fromPOJO({
    //     name: "ROOM ONE",
    //     maxNumberOfUsers: 3
    //   }),
    //
    //   Room.fromPOJO({
    //     name: "ROOM TWO",
    //     maxNumberOfUsers: 3
    //   })
    // ];
  }

}
