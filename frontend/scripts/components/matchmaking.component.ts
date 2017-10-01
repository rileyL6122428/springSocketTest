import { Component, Inject } from '@angular/core';
import { Room } from '../domain/Room';
import { Message } from '@stomp/stompjs';
import { StompService, StompState, StompConfig } from '@stomp/ng2-stompjs';
import { Subscription } from 'rxjs/Subscription';

import { StompServiceFacade } from '../stomp-module/services/stomp.service.facade';

@Component({
  template: `
    <section id="connection-buttons">
      <button id="connect-button">Connect</button>
      <button id="disconnect-button">Disconnect</button>
    </section>

    <section id="rooms">
      <h3>Connected</h3>

      <p>x people waiting</p>

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

  constructor(@Inject(StompServiceFacade) private stompService: StompServiceFacade) {
    console.log("HELLO")
    debugger
    //send to queue to get stats
    this.stompService.subscribe("/topic/matchmaking-stats", (messageBody: string) => {
      console.log(messageBody);
      debugger
      console.log(messageBody);
    });

    debugger
    this.stompService.publish("/app/matchmaking/enter", { name: "test" });
  }

}
