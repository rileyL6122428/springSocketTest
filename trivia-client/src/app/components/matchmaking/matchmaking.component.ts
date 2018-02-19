import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatchmakingStats } from '../../domain/matchmaking/matchmaking-stats';
import { User } from '../../domain/user/user';
import { UserService } from '../../services/user.service';
import { MatchmakingService } from '../../services/matchmaking.service';
import { SubscribingComponent } from '../base/subscribing.component';

@Component({
  selector: 'app-matchmaking',
  templateUrl: './matchmaking.component.html',
  styleUrls: ['./matchmaking.component.css']
})
export class MatchmakingComponent extends SubscribingComponent implements OnInit {

  private matchmakingStats: MatchmakingStats;

  constructor(
    private router: Router,
    private matchmakingService: MatchmakingService
  ) {
    super();
  }

  ngOnInit(): void {
    this.addSubscriptions(
      this.matchmakingService.getMatchmakingStats().subscribe((stats: MatchmakingStats) => {
        this.matchmakingStats = stats;
      }),

      this.matchmakingService.subscribeToMatchmaking().subscribe((stats: MatchmakingStats) => {
        this.matchmakingStats = stats;
      })
    );
  }

  joinRoom(roomName: string): void {
    let joinRoomSub = this.matchmakingService.joinRoom(roomName)
      .subscribe((requestSuccessful: boolean) => {
        if(requestSuccessful) this.router.navigateByUrl(`/room/${roomName}`);
          joinRoomSub.unsubscribe();
      });
  }

  pluralMatchmakingUsers(): boolean {
    return this.matchmakingStats.unplacedUserTotal > 1;
  }

}
