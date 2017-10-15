import { Component, Inject, OnInit, OnDestroy } from '@angular/core';
import { StompServiceFacade } from '../stomp-module/services/stomp.service.facade';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Room } from '../domain/Room';

@Component({
  template: `
    <h1>{{room.getName()}}</h1>
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
    @Inject(StompServiceFacade) private stompService: StompServiceFacade
  ) {
    this.subscriptions = new Array<Subscription>();
    this.room = new Room();
  }

  ngOnInit(): void {
    let paramsSubsciption = this.route.params.subscribe((params) => {
      this.subscribeToRoomMessages(params);
      this.room.setName(params['roomName']);
    });

    this.subscriptions.push(paramsSubsciption);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription =>  subscription.unsubscribe());
  }

  private subscribeToRoomMessages(routeParams: Object): void {
    let roomSubscriptionUrl = "/topic/room/" + routeParams['roomName'];
    // let roomSubscriptionUrl = "/app/room/" + routeParams['roomName'];
    let roomSubscription = this.stompService.subscribe(roomSubscriptionUrl, (messageBody: Object) => {
      this.handleRoomMessage(messageBody);
    });

    this.subscriptions.push(roomSubscription);
  }

  private handleRoomMessage(roomPOJO: Object): void {
    this.room = Room.fromPOJO(roomPOJO);
    console.log(roomPOJO);
  }

  private sendChatMessage() {
    console.log(this.messageBody);
    this.stompService.publish(
      "/app/room/" + this.room.getName() + "/send-message",
      { messageBody: this.messageBody }
    );
    this.messageBody = "";
  }
}
