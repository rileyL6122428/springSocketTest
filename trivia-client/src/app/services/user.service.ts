import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/observable';
import 'rxjs/add/operator/map'
import { User } from '../domain/user/User';

@Injectable()
export class UserService {

  constructor(
    private http: Http
  ) { }

  getUser(): Observable<User> {
    return this.http.get('/user').map((response) => {
      if(response['status'] === 200)
        return response.json() as User;
      else
        return null;
    });
  }

}
