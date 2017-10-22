import { Component, Inject, OnInit, OnDestroy } from '@angular/core';
import { StompServiceFacade } from '../stomp-module/services/stomp.service.facade';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Room } from '../domain/Room';
import { Http } from '@angular/http';
import { Router } from '@angular/router';

@Component({
  template: `
    <h1>{{room.getName()}}</h1>

    <button (click)="leaveRoom()">Leave Room</button>

    <section id="chat-room">
      <section id="message-list" *ngIf="!!room">
        <ul class="message-list" #scrollMe [scrollTop]="scrollMe.scrollHeight">
          <li *ngFor="let message of room.getMessages()">
            <p>{{message.getBody()}}</p>
            <p>{{message.getSenderName()}} {{message.getTimestamp()}}</p>
          </li>
        </ul>
      </section>

      <section id="message-submission">
        <textarea [(ngModel)]="messageBody"></textarea>
        <button (click)="sendChatMessage()"></button>
      </section>
    </section>
  `
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
        debugger
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
