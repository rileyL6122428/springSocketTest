import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { User } from './domain/user/user';
import { StompInitializer } from './services/stomp/stomp.initializer';
import { SubscribingComponent } from './components/base/subscribing.component';
import { SessionService } from './services/session/session.service';
import { SessionHttpUtil } from './services/session/session.http';

@Component({
  selector: 'app-root',
  template: `<router-outlet></router-outlet>`
})
export class AppComponent extends SubscribingComponent implements OnInit {

  constructor(
    private stompInitializer: StompInitializer,
    private sessionHttp: SessionHttpUtil
  ) {
    super();
  }

  ngOnInit(): void {
    this.addSubscriptions(
      this.sessionHttp.createSession()
        .subscribe((sessionCreated: boolean) => {
          if (sessionCreated) {
            this.stompInitializer.setupStompService();
          }
        })
    );
  }

}
