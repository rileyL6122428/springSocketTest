import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class SessionHttpUtil {

  constructor(
    private http: Http,
  ) { }

  createSession(): Observable<boolean> {
    return this.http.post(`/session`, null)
      .map((response) => response[`status`] === 200);
  }

}
