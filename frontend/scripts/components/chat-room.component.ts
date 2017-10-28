import { Component, OnInit, OnDestroy } from '@angular/core';
import { StompServiceFacade } from '../stomp-module/services/stomp.service.facade';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Room } from '../domain/Room';
import { Http } from '@angular/http';
import { Router } from '@angular/router';
import { SubscribingComponentBase } from './SubscribingComponentBase';
import { RoomService } from '../services/room.service';

let chatRoomTemplate: string = require('./chat-room.html');

@Component({
  template: chatRoomTemplate
})
export class ChatRoomComponent extends SubscribingComponentBase implements OnInit, OnDestroy {

  private room: Room;
  private messageBody: string;

  constructor(
    private route: ActivatedRoute,
    private stompService: StompServiceFacade,
    private http: Http,
    private router: Router,
    private roomService: RoomService
  ) {
    super();
    this.room = new Room();
  }

  ngOnInit(): void {
    this.setSubscriptions(
      this.createSubscriptions()
    );
  }

  private createSubscriptions(): Array<Subscription> {
    let routeSubscription: Subscription;
    let roomMessageSubscription: Subscription;
    let getRoomSubscription: Subscription;

    routeSubscription = this.route.params.subscribe((params) => {
      roomMessageSubscription = this.subscribeToRoomMessages(params['roomName']),
      getRoomSubscription = this.getRoom(params['roomName'])
   });

   return [
     routeSubscription,
     roomMessageSubscription,
     getRoomSubscription
   ];
  }

  private getRoom(roomName: string): Subscription {
    return this.http.get(`/room/${roomName}`)
      .subscribe((response) => {
        if(response.status === 200) {
          let roomPOJO = response.json();
          this.room = Room.fromPOJO(roomPOJO);
        }
      });
  }

  private subscribeToRoomMessages(roomName: string): Subscription {
    return this.roomService.subscribeToRoomMessages({
      roomName: roomName,
      success: (roomPOJO) => { this.room = Room.fromPOJO(roomPOJO); }
    })
  }

  private sendChatMessage() {
    if(this.messageBody) {
      this.stompService.publish(this.sendChatMessageURL(), { messageBody: this.messageBody });
      this.messageBody = "";
    }
  }

  private sendChatMessageURL(): string {
    return "/app/room/" + this.room.getName() + "/send-message";
  }

  private leaveRoom(): void {
    this.roomService.leaveRoom({
      roomName: this.room.getName(),
      success: () => { this.router.navigateByUrl("/home"); }
    });
  }
}
