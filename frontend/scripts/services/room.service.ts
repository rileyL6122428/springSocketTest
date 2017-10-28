import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Subscription } from 'rxjs/Subscription';
import { Room } from '../domain/Room';
import { StompServiceFacade } from '../stomp-module/services/stomp.service.facade';

@Injectable()
export class RoomService {

  constructor(
    private stompService: StompServiceFacade
  ){ }

  public subscribeToRoomMessages(params: { roomName: string, success: Function }): Subscription {
    return this.stompService.subscribe(
      `/topic/room/${params.roomName}`,
      params.success
    );
  }

}
