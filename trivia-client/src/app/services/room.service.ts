import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { RoomFactory } from '../domain/room/room.factory';
import { Observable } from 'rxjs/Observable';
import { Room } from '../domain/room/room';

@Injectable()
export class RoomService {

  constructor(
    private roomFactory: RoomFactory,
    private http: Http
  ) { }

  fetchRoom(name: string): Observable<Room> {
    return this.http.get(`/room/${name}`).map((response) => {
      if(response[`status`] === 200) {
        return this.roomFactory.fromPOJO(response.json());
      }
      else
        return null;
    });
  }

}
