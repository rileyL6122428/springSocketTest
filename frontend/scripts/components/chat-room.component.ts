import { Component, Inject, OnInit, OnDestroy } from '@angular/core';
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
    @Inject(ActivatedRoute) private route: ActivatedRoute
  ) {
    this.subscriptions = new Array<Subscription>();
  }

  ngOnInit(): void {
    let paramsSubsciption = this.route.params.subscribe((params) => {
      //Subscribe to room
      //Send Join Message
    });

    this.subscriptions.push(paramsSubsciption);
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription =>  subscription.unsubscribe());
  }

}
