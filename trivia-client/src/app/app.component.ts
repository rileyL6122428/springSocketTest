import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { User } from './domain/user/user';
import { UserService } from './services/user.service';
import { StompInitializer } from './services/stomp/stomp.initializer';
import { SubscribingComponent } from './components/base/subscribing.component';
import { SessionService } from './services/session/session.service';

@Component({
  selector: 'app-root',
  template: `
    <section>
      <h3 id="welcome-user">Welcome {{username}}</h3>
    </section>

    <router-outlet></router-outlet>
  `
})
export class AppComponent extends SubscribingComponent implements OnInit {

  private user: User;

  constructor(
    private userService: UserService,
    private stompInitializer: StompInitializer,
    private sessionService: SessionService
  ) {
    super();
  }

  ngOnInit(): void {
    this.addSubscriptions(
      this.sessionService.createSession().subscribe((sessionCreated: boolean) => {
        if(sessionCreated) {
          this.stompInitializer.setupStompService();
          this.userService.getUser().subscribe((user: User) => {
            this.user = user;
          })
        }
      })
    );
  }

  get username(): string {
    return this.user ? this.user.name : "...";
  }

}
