import { Component, OnInit, Inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { Room } from '../../domain/room/room';
import { RoomService } from '../../services/room.service';
import { SubscribingComponent } from '../base/subscribing.component';
import { Pokemon } from '../../domain/pokemon/pokemon';

@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent extends SubscribingComponent implements OnInit {

  private roomName: string;
  private room: Room;
  private mascot: Pokemon;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private roomService: RoomService
  ) {
    super();
    this.mascot = new Pokemon('Charizard');
  }

  ngOnInit(): void {
    this.addSubscriptions(
      this.route.params.subscribe((params) => {
        this.roomName = params['name'];
      })
    );
  }

  leaveRoom(): void {
    const leaveRoomSub = this.roomService.leaveRoom(this.roomName).subscribe((successfullyLeftRoom: boolean) => {
      leaveRoomSub.unsubscribe();
      if (successfullyLeftRoom) {
        this.router.navigateByUrl('/matchmaking');
      }
    });
  }

}
