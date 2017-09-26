import { Component, Inject } from '@angular/core';
import { Room } from '../domain/Room';
import * as Socket from 'SockJS-client'

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
    let test = new Socket("helloWorld");
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
