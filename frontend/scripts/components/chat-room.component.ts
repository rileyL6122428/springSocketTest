import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Room } from '../domain/Room';
import { Router } from '@angular/router';
import { SubscribingComponentBase } from './subscribing.component.base';
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
    private router: Router,
    private roomService: RoomService
  ) {
    super();
    this.room = new Room();
  }

  ngOnInit(): void {
    let routeSubscription: Subscription;
    let roomMessageSubscription: Subscription;

    routeSubscription = this.route.params.subscribe((params) => {
      this.getRoom(params['roomName']);
      roomMessageSubscription = this.subscribeToRoomMessages(params['roomName']);
   });

    this.setSubscriptions([
      routeSubscription,
      roomMessageSubscription
    ]);
  }

  private getRoom(roomName: string): void {
    this.roomService.getRoom({
      roomName: roomName,
      success: (room: Room) => { this.room = room; }
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
      this.roomService.publishRoomMessage({
        roomName: this.room.getName(),
        messageBody: this.messageBody
      });

      this.messageBody = "";
    }
  }

  private leaveRoom(): void {
    this.roomService.leaveRoom({
      roomName: this.room.getName(),
      success: () => { this.router.navigateByUrl("/home"); }
    });
  }
}
