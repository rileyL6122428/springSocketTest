import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { StompServiceFacade } from '../stomp-module/services/stomp.service.facade';
import { Room } from '../domain/Room';
import { Subscription } from 'rxjs/subscription';

@Injectable()
export class MatchmakingService {

  constructor(
    private http: Http,
    private stompService: StompServiceFacade
  ) { }

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

  public subscribeToMatchmaking(params: { onMessageReceived: Function }): Subscription {
    return this.stompService.subscribe("/topic/matchmaking", (messageBody: Object) => {
      params.onMessageReceived(messageBody);
    });
  }

}
