import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class UserService {

  constructor(
    private http: Http
  ) { }

  getUser(): void {
    debugger
    this.http.get('/user');
  }

}
