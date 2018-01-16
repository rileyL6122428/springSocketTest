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
  // private _room: Room;
  private roomName: string;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private roomService: RoomService
  ) { }

  ngOnInit(): void {
    this.subscriptions = [
      this.route.params.subscribe((params) => {
        this.roomName = params["name"];
      })
    ];
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((subscription: Subscription) => {
      subscription.unsubscribe();
    });
  }

  leaveRoom(): void {
    let leaveRoomSub = this.roomService.leaveRoom(this.roomName).subscribe((successfullyLeftRoom: boolean) => {
      leaveRoomSub.unsubscribe();
      if(successfullyLeftRoom)
        this.router.navigateByUrl("/matchmaking");
    });
  }

}
