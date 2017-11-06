import { Component, OnInit, OnDestroy } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from '../domain/user/user';
import { Subscription } from 'rxjs/subscription';

@Component({
  selector: 'app-matchmaking',
  templateUrl: './matchmaking.component.html',
  styleUrls: ['./matchmaking.component.css']
})
export class MatchmakingComponent implements OnInit, OnDestroy {

  private user: User;
  private subscriptions: Array<Subscription>;

  constructor(
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.subscriptions = new Array<Subscription>();
    let getUserSubscription = this.userService.getUser().subscribe((user: User) => {
      this.user = user;
    });

    this.subscriptions.push(getUserSubscription);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => {
      subscription.unsubscribe();
    });
  }

}
