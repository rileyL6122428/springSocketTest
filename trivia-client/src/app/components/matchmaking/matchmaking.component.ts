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

  private matchmakingStats: MatchmakingStats;
  private subscriptions: Array<Subscription>;

  constructor(
    private router: Router,
    private matchmakingService: MatchmakingService
  ) { }

  ngOnInit(): void {
    this.subscriptions = [
      this.matchmakingService.getMatchmakingStats().subscribe((stats: MatchmakingStats) => {
        this.matchmakingStats = stats;
      }),

      this.matchmakingService.subscribeToMatchmaking().subscribe((stats: MatchmakingStats) => {
        this.matchmakingStats = stats;
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

}
