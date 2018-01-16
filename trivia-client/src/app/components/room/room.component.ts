import { Component, OnInit, OnDestroy, Inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs/Subscription';
import { Room } from '../../domain/room/room';
import { RoomService } from '../../services/room.service';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit, OnDestroy {

  private subscriptions: Array<Subscription>;
  private _room: Room;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private roomService: RoomService
  ) { }

  ngOnInit(): void {
    let routeSubscription: Subscription;
    let fetchRoomSubscription: Subscription;
    let roomStompSubscription: Subscription;

    routeSubscription = this.route.params.subscribe((params) => {
      fetchRoomSubscription = this.roomService.fetchRoom(params['name']).subscribe((room) => {
        this._room = room;
      });

      roomStompSubscription = this.roomService.getRoomStompListener(params['name']).subscribe((room) => {
        this._room = room;
      });
    });

    this.subscriptions = [
      routeSubscription,
      fetchRoomSubscription,
      roomStompSubscription
    ];
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription: Subscription) => {
      subscription.unsubscribe();
    });
  }

  leaveRoom(): void {
    let leaveRoomSub = this.roomService.leaveRoom(this.room.name).subscribe((successfullyLeftRoom: boolean) => {
      leaveRoomSub.unsubscribe();
      if(successfullyLeftRoom)
        this.router.navigateByUrl("/matchmaking");
    });
  }

  get room(): Room {
    return this._room;
  }

}
