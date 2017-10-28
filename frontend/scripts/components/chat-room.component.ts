import { Component, Inject, OnInit, OnDestroy } from '@angular/core';
import { StompServiceFacade } from '../stomp-module/services/stomp.service.facade';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Room } from '../domain/Room';
import { Http } from '@angular/http';
import { Router } from '@angular/router';

let chatRoomTemplate: string = require('./chat-room.html');

@Component({
  template: chatRoomTemplate
})
export class ChatRoomComponent implements OnInit, OnDestroy {

  private subscriptions: Array<Subscription>;
  private room: Room;
  private messageBody: string;

  constructor(
    @Inject(ActivatedRoute) private route: ActivatedRoute,
    @Inject(StompServiceFacade) private stompService: StompServiceFacade,
    @Inject(Http) private http: Http,
    @Inject(Router) private router: Router
  ) {
    this.subscriptions = new Array<Subscription>();
    this.room = new Room();
  }

  ngOnInit(): void {
    let paramsSubsciption = this.route.params.subscribe((params) => {
      this.subscribeToRoomMessages(params);
      this.room.setName(params['roomName']);
      this.getRoom();
    });

    this.subscriptions.push(paramsSubsciption);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription =>  subscription.unsubscribe());
  }

  private getRoom(): void {
    let getRoomSubscription:Subscription = this.http.get(this.getRoomEndpoint())
      .subscribe((response) => {
        if(response.status === 200) {
          let roomPOJO = response.json();
          this.room = Room.fromPOJO(roomPOJO);
        }
      });

      this.subscriptions.push(getRoomSubscription);
  }

  private getRoomEndpoint(): string {
    return "/room/" + this.room.getName();
  }

  private subscribeToRoomMessages(routeParams: Object): void {
    let roomSubscriptionUrl = "/topic/room/" + routeParams['roomName'];
    let roomSubscription = this.stompService.subscribe(roomSubscriptionUrl, (roomPOJO: Object) => {
      this.room = Room.fromPOJO(roomPOJO);
    });

    this.subscriptions.push(roomSubscription);
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
