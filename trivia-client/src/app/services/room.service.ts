import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { RoomFactory } from '../domain/room/room.factory';
import { Observable } from 'rxjs/Observable';
import { Room } from '../domain/room/room';
import { StompService } from '@stomp/ng2-stompjs';
import { CookieService } from 'angular2-cookie/services/cookies.service';

@Injectable()
export class RoomService {

  constructor(
    private roomFactory: RoomFactory,
    private http: Http,
    private stompService: StompService,
    private cookieService: CookieService
  ) { }

  fetchRoom(roomName: string): Observable<Room> {
    return this.http.get(`/room/${roomName}`).map((response) => {
      if(response[`status`] === 200)
        return this.roomFactory.fromPOJO(response.json());
      else
        return null;
    });
  }

  leaveRoom(roomName: string): Observable<boolean> {
    return this.http.post(`/room/${roomName}/leave`, {}).map((response) => {
      return response[`status`] === 200;
    });
  }

  getRoomStompListener(roomName: string): Observable<Room> {
    let headers = { testHeader: this.cookieService.get("TRIVIA_SESSION_COOKIE") };
    return this.stompService.subscribe(`/topic/room/${roomName}`, headers)
      .map((message) => {
        let messagePayload = JSON.parse(message.body);
        return this.roomFactory.fromPOJO(messagePayload);
      });
  }

  sendMessage(params: { roomName: string, messageBody: string }): void {
    let endpoint = `/app/room/${params.roomName}/send-message`;
    let message = JSON.stringify({ messageBody: params.messageBody});
    let headers = { testHeader: this.cookieService.get("TRIVIA_SESSION_COOKIE") };

    this.stompService.publish(endpoint, message, headers);
  }

}
