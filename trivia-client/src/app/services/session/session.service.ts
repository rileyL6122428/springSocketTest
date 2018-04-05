import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { CookieService } from 'angular2-cookie/services/cookies.service';

@Injectable()
export class SessionService {

  constructor(
    private http: Http,
    private cookieService: CookieService
  ) {}

  createSession(): Observable<boolean> {
    return this.http.post(`/session`, { TIVIA_SESSION_COOKIE: this.sessionToken })
      .map((response) => {
        return response[`status`] === 200;
      });
  }

  get sessionToken(): string {
    return this.cookieService.get('TRIVIA_SESSION_COOKIE');
  }

}
