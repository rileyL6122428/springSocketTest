import { Component, OnInit, OnDestroy } from '@angular/core';
import { Http } from '@angular/http';
import { Router } from '@angular/router';
import { Message } from '@stomp/stompjs';
import { Subscription } from 'rxjs/Subscription';
import { StompServiceFacade } from '../stomp-module/services/stomp.service.facade';
import { UserService } from '../services/user.service';
import { Room } from '../domain/Room';
import { User } from '../domain/User';
import { SubscribingComponentBase } from './SubscribingComponentBase';

let matchmakingTemplate = require('./matchmaking.html');

@Component({
  template: matchmakingTemplate
})
export class MatchmakingComponent extends SubscribingComponentBase implements OnInit, OnDestroy {

  private unplacedUsersCount: number;
  private rooms: Array<Room>;
  private user: User;

  constructor(
    private stompService: StompServiceFacade,
    private http: Http,
    private router: Router,
    private userService: UserService
  ) {
    super();
  }

  ngOnInit(): void {
    this.setSubscriptions(
      this.getMatchmakingStats(),
      this.getUser(),
      this.subscribeToMatchmaking()
    );
  }

  private subscribeToMatchmaking(): Subscription {
    return this.stompService.subscribe("/topic/matchmaking", (messageBody: Object) => {
      this.setMatchmakingStats(messageBody);
    });
  }

  private getMatchmakingStats(): Subscription {
    return this.http.get("/matchmaking/stats").subscribe((response) => {
      let responseBody: Object =  response.json();
      this.setMatchmakingStats(responseBody);
    });
  }

  private getUser(): Subscription {
    return this.http.get("/user").subscribe((response) => {
      let userPOJO: Object = response.json();
      let user: User = User.fromPOJO(userPOJO);
      this.user = user;
      this.userService.storeUser(user);
    });
  }

  private setMatchmakingStats(responseBody: Object): void {
    this.setRooms(responseBody);
    this.unplacedUsersCount = responseBody['userTotal'] - this.placedUserTotal();
  }

  private setRooms(messageBody: Object): void {
    let receivedRooms: Map<string, Room> = messageBody['rooms'];
    this.rooms = new Array<Room>();

    for(let roomName in receivedRooms) {
      let roomPOJO = receivedRooms[roomName];
      let room: Room = Room.fromPOJO(roomPOJO);

      this.rooms.push(room);
    }
  }

  private placedUserTotal(): number {
    let placedUserTotal = 0;

    this.rooms.forEach((room: Room) => {
      placedUserTotal += room.getTotalNumberOfUsers();
    });

    return placedUserTotal;
  }

  private joinChatRoom(roomName: string): void {
    this.http.post("/join-chat-room", { roomName: roomName }, {})

      .subscribe((response) => {
        if(response.status === 200)
          this.joinRoom(response.json())
        else
          this.showJoinChatFailureModal(response.json());
      });
  }

  private joinRoom(responseBody: Object)  {
    let targetRoom = Room.fromPOJO(responseBody['room']);
    this.router.navigateByUrl('/chat/' + targetRoom.getName());
  }

  private showJoinChatFailureModal(responseBody: Object) {
    console.log(responseBody);
  }

}
