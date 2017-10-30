import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { UserService } from '../services/user.service';
import { MatchmakingService } from '../services/matchmaking.service';
import { Room } from '../domain/Room';
import { User } from '../domain/User';
import { MatchmakingStats } from '../domain/MatchmakingStats';
import { SubscribingComponentBase } from './subscribing.component.base';

@Component({
  template: require('./matchmaking.html')
})
export class MatchmakingComponent extends SubscribingComponentBase implements OnInit, OnDestroy {

  private matchmakingStats: MatchmakingStats;
  private user: User;

  constructor(
    private router: Router,
    private userService: UserService,
    private matchmakingService: MatchmakingService
  ) {
    super();
  }

  ngOnInit(): void {
    this.getUser();
    this.getMatchmakingStats();

    this.setSubscriptions([
      this.subscribeToMatchmaking()
    ]);
  }

  private subscribeToMatchmaking(): Subscription {
    return this.matchmakingService.subscribeToMatchmaking({
      onMessageReceived: (messageBody: Object) => {
        this.matchmakingStats = new MatchmakingStats(messageBody);
      }
    });
  }

  private getMatchmakingStats(): void {
    this.matchmakingService.getMatchmakingStats({
      success: (responseBody) => {
        this.matchmakingStats = new MatchmakingStats(responseBody);
      }
    });
  }

  private getUser(): void {
    this.userService.getUser({
      success: (user: User) => { this.user = user; }
    });
  }

  private joinChatRoom(roomName: string): void {
    this.matchmakingService.joinChatRoom({
      roomName: roomName,
      success: (room: Room) => { this.router.navigateByUrl(`/chat/${room.getName()}`); }
    });
  }
}
