import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { User } from './domain/user/user';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  template: `
    <section>
      <h3 id="welcome-user">Welcome {{username}}</h3>
    </section>

    <router-outlet></router-outlet>
  `
})
export class AppComponent implements OnInit, OnDestroy {

  private user: User;
  private subscriptions: Array<Subscription>;

  constructor(
    private userService: UserService,
  ) {}

  ngOnInit(): void {
    this.subscriptions = [
      this.userService.getUser().subscribe((user: User) => {
        this.user = user;
      })
    ];
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription: Subscription) => {
      subscription.unsubscribe();
    });
  }

  get username(): string {
    return this.user ? this.user.name : "...";
  }

}
