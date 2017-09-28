import { Component, Inject } from '@angular/core';
import { Room } from '../domain/Room';
import * as SockJS from 'SockJS-client'
import * as Stomp from 'stompjs';
import {StompService, StompState, StompConfig} from '@stomp/ng2-stompjs';

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
  // private sockJS: ;

  constructor() {
    // let test = new SockJS("helloWorld");
    // let stompClient: Stomp.Client = Stomp.overWS("/matchmaking");
    // let x: SockJS.Socket = new SockJS("sup");
    // let y = Stomp.over(x);

    const stompConfig: StompConfig = {
      // Which server?
      url: 'ws://localhost:8090/matchmaking/websocket',

      // Headers
      // Typical keys: login, passcode, host
      headers: {
        login: 'guest',
        passcode: 'guest'
      },

      // How often to heartbeat?
      // Interval in milliseconds, set to 0 to disable
      heartbeat_in: 0, // Typical value 0 - disabled
      heartbeat_out: 20000, // Typical value 20000 - every 20 seconds

      // Wait in milliseconds before attempting auto reconnect
      // Set to 0 to disable
      // Typical value 5000 (5 seconds)
      reconnect_delay: 5000,

      // Will log diagnostics on console
      debug: true
    };
    let x = new StompService(stompConfig);

    //This is the socket, now you need Stomp over (see old-index.html)
    //look up types in Typescprt 2.0
    //https://stackoverflow.com/questions/37548066/typescript-typings-in-npm-types-org-packages
    //it looks like you just need the JS package and then the typeing
    // need to learn how that works
    debugger
    this.rooms =  [
      Room.fromPOJO({
        name: "ROOM ONE",
        maxNumberOfUsers: 3
      }),

      Room.fromPOJO({
        name: "ROOM TWO",
        maxNumberOfUsers: 3
      })
    ];
  }

}
