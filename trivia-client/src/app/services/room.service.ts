import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { GameFactory } from '../domain/game/game.factory';
import { Observable } from 'rxjs/Observable';
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
    private gameFactory: GameFactory,
    private chatFactory: ChatFactory,
    private http: Http,
    private stompService: StompRService,
    private cookieService: CookieService
  ) { }

  fetchChat(roomName: string): Observable<Chat> {
    return this.http.get(`/room/${roomName}/chat`).map((response) => {
      if(response[`status`] === 200)
        return this.chatFactory.fromPOJO(response.json());
      else
        return null;
    });
  }

  leaveRoom(roomName: string): Observable<boolean> {
    return this.http.post(`/room/${roomName}/leave`, {}).map((response) => {
      return response[`status`] === 200;
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
