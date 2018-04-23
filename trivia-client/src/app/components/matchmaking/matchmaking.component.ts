import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatchmakingStream } from '../../services/matchmaking/matchmaking.stream';
import { Room } from '../../domain/room/room';
import { RoomStore } from '../../stores/room/room.store';
import { MatchmakingHttpUtil } from '../../services/matchmaking/matchmaking.http';
import { SubscribingComponent } from '../base/subscribing.component';

@Component({
  selector: 'app-matchmaking',
  templateUrl: './matchmaking.component.html',
  styleUrls: ['./matchmaking.component.css']
})
export class MatchmakingComponent extends SubscribingComponent implements OnInit {

  private rooms: Array<Room>;
  private selectedRoom: Room;

  constructor(
    private router: Router,
    private matchmakingStream: MatchmakingStream,
    private matchmakingHttp: MatchmakingHttpUtil
  ) {
    super();
  }

  ngOnInit(): void {
    this.subscriptions = this.matchmakingStream.subscribe(
      (roomStore) => this.rooms = roomStore.recordsAsList()
    );
  }

  toggleRoom(room: Room): void {
    this.selectedRoom ? this.returnToMatchmaking() : this.joinRoom(room);
  }

  private returnToMatchmaking(): void {
    this.matchmakingHttp.leaveRoom(this.selectedRoom)
      .subscribe((hasLeftRoom: boolean) => {
        if (hasLeftRoom) {
          this.selectedRoom = undefined;
          this.router.navigateByUrl(`/matchmaking`);
        }
      });
  }

  private joinRoom(room: Room) {
    this.matchmakingHttp.joinRoom(room)
      .subscribe((joinedRoom: boolean) => {
        if (joinedRoom) {
          this.selectedRoom = room;
          this.router.navigateByUrl(`/matchmaking/room/${room.name}`);
        }
      });
  }

  roomDisplayable(room: Room): boolean {
    return !this.selectedRoom || this.selectedRoom.equals(room);
  }

  trackRoomByName(index: number, room: Room): string {
    return room.name;
  }

}
