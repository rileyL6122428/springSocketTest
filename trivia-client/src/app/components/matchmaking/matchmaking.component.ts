import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatchmakingStats } from '../../domain/matchmaking/matchmaking-stats';
import { MatchmakingService } from '../../services/matchmaking.service';
import { Room } from '../../domain/room/room';
import { RoomStore } from '../../stores/room/room.store';
import { RemovableSubscription } from '../base/removable-subscription';

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
    private matchmakingService: MatchmakingService,
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
    this.matchmakingService.leaveRoom(this.selectedRoom).subscribe((successfullyLeft: boolean) => {
      if (successfullyLeft) {
        this.selectedRoom = undefined;
        this.router.navigateByUrl(`/matchmaking`);
        this.subscribeToMatchmaking();
      }
    });
  }

  private joinRoom(room: Room) {
    this.matchmakingService.joinRoom(room).subscribe((joinedRoom: boolean) => {
      if (joinedRoom) {
        this.selectedRoom = room;
        this.router.navigateByUrl(`/matchmaking/room/${room.name}`);
        this.matchmakingSub.unsubscribe();
      }
    });
  }

  private subscribeToMatchmaking(): void {
    this.matchmakingSub = this.matchmakingService.stream((roomStore) => {
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
