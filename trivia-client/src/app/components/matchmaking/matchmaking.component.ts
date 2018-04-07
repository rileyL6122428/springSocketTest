import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatchmakingStats } from '../../domain/matchmaking/matchmaking-stats';
import { MatchmakingStream } from '../../services/matchmaking/matchmaking.stream';
import { Room } from '../../domain/room/room';
import { RoomStore } from '../../stores/room/room.store';
import { RemovableSubscription } from '../base/removable-subscription';
import { MatchmakingHttpUtil } from '../../services/matchmaking/matchmaking.http';

@Component({
  selector: 'app-matchmaking',
  templateUrl: './matchmaking.component.html',
  styleUrls: ['./matchmaking.component.css']
})
export class MatchmakingComponent implements OnInit {

  private rooms: Array<Room>;
  private selectedRoom: Room;
  private matchmakingSub: RemovableSubscription;

  constructor(
    private router: Router,
    private matchmakingStream: MatchmakingStream,
    private matchmakingHttp: MatchmakingHttpUtil
  ) { }

  ngOnInit(): void {
    this.subscribeToMatchmaking();
  }

  toggleRoom(room: Room): void {
    if (this.selectedRoom) {
      this.returnToMatchmaking();
    } else {
      this.joinRoom(room);
    }
  }

  private returnToMatchmaking(): void {
    this.matchmakingHttp.leaveRoom(this.selectedRoom)
      .subscribe((leftRoom: boolean) => {
        if (leftRoom) {
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

  private subscribeToMatchmaking(): void {
    this.matchmakingSub = this.matchmakingStream.subscribe((roomStore) => {
      this.rooms = roomStore.recordsAsList();
    });
  }

  roomDisplayable(room: Room): boolean {
    return !this.selectedRoom || this.selectedRoom.equals(room);
  }

  trackRoomByName(index: number, room: Room): string {
    return room.name;
  }

}
