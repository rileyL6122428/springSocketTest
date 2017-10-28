import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Room } from '../domain/Room';

@Injectable()
export class MatchmakingService {

  constructor(
    private http: Http
  ) {

  }

  public getMatchmakingStats(params: { success: Function }): void {
    let requestSubscription = this.http.get("/matchmaking/stats").subscribe((response) => {
      if(response['status'] === 200) {
        let responseBody: Object =  response.json();
        params.success(responseBody);
      }

      requestSubscription.unsubscribe();
    });
  }

  public joinChatRoom(params: { roomName: string, success: Function }): void {
    let requestSubscription = this.http.post("/join-chat-room", { roomName: params.roomName }, {})
      .subscribe((response) => {
        if(response['status'] === 200) {
          let roomPOJO: Object = response.json();
          let room: Room = Room.fromPOJO(roomPOJO);
          params.success(room);
        }

        requestSubscription.unsubscribe();
      });
  }

}
