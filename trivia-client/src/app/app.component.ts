import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { User } from './domain/user/user';
import { StompInitializer } from './services/stomp/stomp.initializer';
import { SubscribingComponent } from './components/base/subscribing.component';
import { SessionService } from './services/session/session.service';

@Component({
  selector: 'app-root',
  template: `<router-outlet></router-outlet>`
})
export class AppComponent extends SubscribingComponent implements OnInit {

  constructor(
    private stompInitializer: StompInitializer,
    private sessionService: SessionService
  ) {
    super();
  }

  ngOnInit(): void {
    this.addSubscriptions(
      this.sessionService.createSession().subscribe((sessionCreated: boolean) => {
        if (sessionCreated) {
          this.stompInitializer.setupStompService();
        }
      })
    );
  }

}
