import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { User } from './domain/user/user';
import { StompInitializer } from './services/stomp/stomp.initializer';
import { SessionService } from './services/session/session.service';
import { SessionHttpUtil } from './services/session/session.http';

@Component({
  selector: 'app-root',
  template: `<router-outlet></router-outlet>`
})
export class AppComponent implements OnInit {

  constructor(
    private stompInitializer: StompInitializer,
    private sessionHttp: SessionHttpUtil
  ) { }

  ngOnInit(): void {
    this.sessionHttp.createSession()
      .subscribe((sessionCreated: boolean) => {
        if (sessionCreated) {
          this.stompInitializer.setupStompService();
        }
      });
  }

}
