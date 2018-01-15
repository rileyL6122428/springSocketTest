import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { RoomFactory } from '../domain/room/room.factory';
import { GameFactory } from '../domain/game/game.factory';
import { Observable } from 'rxjs/Observable';
import { Room } from '../domain/room/room';
import { Chat } from '../domain/chat/chat';
import { StompRService } from '@stomp/ng2-stompjs';
import { StompHeaders } from '@stomp/stompjs';
import { CookieService } from 'angular2-cookie/services/cookies.service';
import { Game } from '../domain/game/game';
import { Answer } from '../domain/game/answer';
import { ChatFactory } from '../domain/chat/chat.factory';

@Injectable()
export class RoomService {

  constructor(
    private roomFactory: RoomFactory,
    private gameFactory: GameFactory,
    private chatFactory: ChatFactory,
    private http: Http,
    private stompService: StompRService,
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

  getChatStompListener(roomName: string): Observable<Chat> {
    return this.stompService.subscribe(`/topic/room/${roomName}/chat`, this.getStompHeaders())
      .map((message) => {
        let messageBody = JSON.parse(message.body);
        return this.chatFactory.fromPOJO(messageBody);
      });
  }

  sendMessage(params: { roomName: string, messageBody: string }): void {
    let endpoint = `/app/room/${params.roomName}/send-message`;
    let message = JSON.stringify({ messageBody: params.messageBody});

    this.stompService.publish(endpoint, message, this.getStompHeaders());
  }

  getGameStompListener(roomName: string): Observable<Game> {
    return this.stompService.subscribe(`/topic/room/${roomName}/game`, this.getStompHeaders())
      .map((message) => {
        let messageBody = JSON.parse(message.body);
        return this.gameFactory.mapPOJO(messageBody);
      });
  }

  submitGameAnswer(params: { roomName: string, answer: Answer}): void {
    let endpoint = `/app/room/${params.roomName}/submit-answer`;
    let message = JSON.stringify(params.answer);

    this.stompService.publish(endpoint, message, this.getStompHeaders());
  }

  private getStompHeaders(): StompHeaders {
    return { testHeader: this.cookieService.get("TRIVIA_SESSION_COOKIE") };
  }

}
