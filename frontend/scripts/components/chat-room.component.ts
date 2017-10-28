import { Component, OnInit, OnDestroy } from '@angular/core';
import { StompServiceFacade } from '../stomp-module/services/stomp.service.facade';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Room } from '../domain/Room';
import { Http } from '@angular/http';
import { Router } from '@angular/router';
import { SubscribingComponentBase } from './SubscribingComponentBase';

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
    private router: Router
  ) {
    super();
    this.room = new Room();
  }

  ngOnInit(): void {
    this.setSubscriptions(
      this.subscribeToRoomMessages(this.route.params['roomName']),
      this.getRoom(this.route.params['roomName'])
    );
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
    return this.stompService.subscribe(`/topic/room/${roomName}`, (roomPOJO: Object) => {
      this.room = Room.fromPOJO(roomPOJO);
    });
  }

  private sendChatMessage() {
    if(this.messageBody !== "") {
      this.stompService.publish(this.sendChatMessageURL(), { messageBody: this.messageBody });
      this.messageBody = "";
    }
  }

  private sendChatMessageURL(): string {
    return "/app/room/" + this.room.getName() + "/send-message";
  }

  private leaveRoom(): void {
    let leaveRoomSubscription = this.http.post('/room/' + this.room.getName() + "/leave", {})
      .subscribe((response: Object) => {
        if(response['status'] === 200) {
          this.router.navigateByUrl('/home');
        }
        leaveRoomSubscription.unsubscribe();
      });
  }
}
