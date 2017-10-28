import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';
import { Room } from '../domain/Room';
import { StompServiceFacade } from '../stomp-module/services/stomp.service.facade';

@Injectable()
export class RoomService {

  constructor(
    private stompService: StompServiceFacade,
    private http: Http
  ){ }

  public subscribeToRoomMessages(params: { roomName: string, success: Function }): Subscription {
    return this.stompService.subscribe(
      `/topic/room/${params.roomName}`,
      params.success
    );
  }


  public leaveRoom(params: { roomName: string, success: Function }): void {
    let leaveRoomSubscription = this.http.post(`/room/${params.roomName}/leave`, {})
      .subscribe((response: Object) => {
        if(response['status'] === 200) {
          params.success();
        }
        leaveRoomSubscription.unsubscribe();
      });
  }
}
