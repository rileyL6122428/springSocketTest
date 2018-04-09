import { Injectable } from '@angular/core';
import { CookieService } from 'angular2-cookie/services/cookies.service';

@Injectable()
export class SessionService {

  constructor(
    private cookieService: CookieService
  ) {}

  get sessionToken(): string {
    return this.cookieService.get('TRIVIA_SESSION_COOKIE');
  }

}
