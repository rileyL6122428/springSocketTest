import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatchmakingStats } from '../../domain/matchmaking/matchmaking-stats';
import { User } from '../../domain/user/user';
import { UserService } from '../../services/user.service';
import { MatchmakingService } from '../../services/matchmaking.service';
import { SubscribingComponent } from '../base/subscribing.component';
import { Room } from '../../domain/room/room';

@Component({
  selector: 'app-matchmaking',
  templateUrl: './matchmaking.component.html',
  styleUrls: ['./matchmaking.component.css']
})
export class MatchmakingComponent extends SubscribingComponent implements OnInit {

  private matchmakingStats: MatchmakingStats;
  private selectedRoom: Room;

  constructor(
    private router: Router,
    private matchmakingService: MatchmakingService,
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

  toggleRoom(room: Room): void {
    console.log(room.name);
    this.selectedRoom = (this.selectedRoom) ? undefined : room;
    
    // const joinRoomSub = this.matchmakingService.joinRoom(room.name)
      // .subscribe((requestSuccessful: boolean) => {
        // if (requestSuccessful) { this.router.navigateByUrl(`/room/${room.name}`); }
      // });
  }

  pluralMatchmakingUsers(): boolean {
    return this.matchmakingStats.unplacedUserTotal > 1;
  }

}
