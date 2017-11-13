import { Component, OnInit, OnDestroy } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from '../domain/user/user';
import { MatchmakingService } from '../services/matchmaking.service';
import { Subscription } from 'rxjs/subscription';
import { MatchmakingStats } from '../domain/matchmaking/matchmaking-stats';

@Component({
  selector: 'app-matchmaking',
  templateUrl: './matchmaking.component.html',
  styleUrls: ['./matchmaking.component.css']
})
export class MatchmakingComponent implements OnInit, OnDestroy {

  private user: User;
  private matchmakingStats: MatchmakingStats;
  private subscriptions: Array<Subscription>;

  constructor(
    private userService: UserService,
    private matchmakingService: MatchmakingService
  ) { }

  ngOnInit(): void {

    let getUserSubscription = this.userService.getUser().subscribe((user: User) => {
      this.user = user;
    });

    let getStatsSubscription = this.matchmakingService.getMatchmakingStats().subscribe((stats: MatchmakingStats) => {
      this.matchmakingStats = stats;
    });

    this.subscriptions = [
      getUserSubscription,
      getStatsSubscription
    ];
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => {
      subscription.unsubscribe();
    });
  }

}
