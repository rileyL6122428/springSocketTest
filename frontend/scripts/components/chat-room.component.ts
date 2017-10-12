import { Component, Inject, OnInit, OnDestroy } from '@angular/core';
import { StompServiceFacade } from '../stomp-module/services/stomp.service.facade';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Room } from '../domain/Room';

@Component({
  template: `
    <p>THIS IS THE CHAT ROOM COMPONENT</p>
    <section *ngIf="!!room">
      <h3 >{{room.getName()}}</h3>

      <ul>
        <li *ngFor="let message of room.getMessages()">
        {{message.getBody()}}
        </li>
      </ul>
    </section>
  `
})
export class ChatRoomComponent implements OnInit, OnDestroy {

  private subscriptions: Array<Subscription>;
  private room: Room;

  constructor(
    @Inject(ActivatedRoute) private route: ActivatedRoute,
    @Inject(StompServiceFacade) private stompService: StompServiceFacade
  ) {
    this.subscriptions = new Array<Subscription>();
  }

  ngOnInit(): void {
    let paramsSubsciption = this.route.params.subscribe((params) => {
      this.subscribeToRoomMessages(params);
      debugger
      this.stompService.publish("/app/room/" + params['roomName'] + "/enter", {});
    });

    this.subscriptions.push(paramsSubsciption);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription =>  subscription.unsubscribe());
  }

  private subscribeToRoomMessages(routeParams: Object): void {
    let roomSubscriptionUrl = "/topic/room/" + routeParams['roomName'];
    let roomSubscription = this.stompService.subscribe(roomSubscriptionUrl, (messageBody: Object) => {
      this.handleRoomMessage(messageBody);
    });
    this.subscriptions.push(roomSubscription);
  }

  private handleRoomMessage(roomPOJO: Object): void {
    debugger
    this.room = Room.fromPOJO(roomPOJO);
    debugger
    console.log(roomPOJO);
  }

}
