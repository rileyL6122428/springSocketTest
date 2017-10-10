import { Component, Inject, OnInit, OnDestroy } from '@angular/core';
import { StompServiceFacade } from '../stomp-module/services/stomp.service.facade';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { Room } from '../domain/Room';

@Component({
  template: `
    <p>THIS IS THE CHAT ROOM COMPONENT</p>
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

      //Send Join Message
    });

    this.subscriptions.push(paramsSubsciption);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription =>  subscription.unsubscribe());
  }

  private subscribeToRoomMessages(routeParams: Object): void {
    let roomPublishUrl = "app/room/" + routeParams['roomName'];
    let roomSubscription = this.stompService.subscribe(roomPublishUrl, this.handleRoomMessage);
    this.subscriptions.push(roomSubscription);
  }

  private handleRoomMessage(messageBody: Object): void {

  }

}
