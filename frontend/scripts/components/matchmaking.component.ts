import { Component } from '@angular/core';

import { Room } from '../domain/Room';

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
