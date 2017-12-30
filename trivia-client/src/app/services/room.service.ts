import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { RoomFactory } from '../domain/room/room.factory';
import { GameMessageFactory } from '../domain/game-message/game-message.factory';
import { Observable } from 'rxjs/Observable';
import { Room } from '../domain/room/room';
import { StompService } from '@stomp/ng2-stompjs';
import { StompHeaders } from '@stomp/stompjs';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { GameMessage } from '../domain/game-message/game-message';

@Injectable()
export class RoomService {

  constructor(
    private roomFactory: RoomFactory,
    private gameMessageFactory: GameMessageFactory,
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
    return this.stompService.subscribe(`/topic/room/${roomName}`, this.getStompHeaders())
      .map((message) => {
        let messageBody = JSON.parse(message.body);
        return this.roomFactory.fromPOJO(messageBody);
      });
  }

  getGameStompListener(roomName: string): Observable<GameMessage> {
    debugger
    return this.stompService.subscribe(`/topic/room/${roomName}/game`, this.getStompHeaders())
      .map((message) => {
        debugger
        let messageBody = JSON.parse(message.body);
        return this.gameMessageFactory.mapPOJO(messageBody);
      });
  }

  private getStompHeaders(): StompHeaders {
    return { testHeader: this.cookieService.get("TRIVIA_SESSION_COOKIE") };
  }

  sendMessage(params: { roomName: string, messageBody: string }): void {
    let endpoint = `/app/room/${params.roomName}/send-message`;
    let message = JSON.stringify({ messageBody: params.messageBody});
    let headers = { testHeader: this.cookieService.get("TRIVIA_SESSION_COOKIE") };

    this.stompService.publish(endpoint, message, headers);
  }

}
