import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/subscription';
import { MatchmakingStats } from '../../domain/matchmaking/matchmaking-stats';
import { User } from '../../domain/user/user';
import { UserService } from '../../services/user.service';
import { MatchmakingService } from '../../services/matchmaking.service';

@Component({
  selector: 'app-matchmaking',
  templateUrl: './matchmaking.component.html',
  styleUrls: ['./matchmaking.component.css']
})
export class MatchmakingComponent implements OnInit, OnDestroy {

  private _user: User;
  private _matchmakingStats: MatchmakingStats;
  private subscriptions: Array<Subscription>;

  constructor(
    private router: Router,
    private userService: UserService,
    private matchmakingService: MatchmakingService
  ) { }

  ngOnInit(): void {
    this.subscriptions = [
      this.userService.getUser().subscribe((user: User) => {
        this._user = user;
      }),

      this.matchmakingService.getMatchmakingStats().subscribe((stats: MatchmakingStats) => {
        this._matchmakingStats = stats;
      }),

      this.matchmakingService.subscribeToMatchmaking().subscribe((stats: MatchmakingStats) => {
        this._matchmakingStats = stats;
      })
    ];
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription) => {
      subscription.unsubscribe();
    });
  }

  joinRoom(roomName: string): void {
    let joinRoomSub = this.matchmakingService.joinRoom(roomName)
      .subscribe((requestSuccessful: boolean) => {
        if(requestSuccessful) this.router.navigateByUrl(`/room/${roomName}`);
        joinRoomSub.unsubscribe();
      });
  }

  get user(): User {
    return this._user;
  }

  get matchmakingStats(): MatchmakingStats {
    return this._matchmakingStats;
  }

}
