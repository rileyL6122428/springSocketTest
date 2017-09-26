import { Component } from '@angular/core';

@Component({
  template: `
    <section id="connection-buttons">
      <button id="connect-button">Connect</button>
      <button id="disconnect-button">Disconnect</button>
    </section>

    <section id="rooms" *ngIf="false">
      Hello
    </section>
  `
})
export class MatchmakingComponent {

}
