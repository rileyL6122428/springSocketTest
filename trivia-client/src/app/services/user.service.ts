import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/observable';
import 'rxjs/add/operator/map'
import { User } from '../domain/user/User';
import { UserFactory } from '../domain/user/User.factory';

@Injectable()
export class UserService {

  constructor(
    private http: Http,
    private userFactory: UserFactory
  ) { }

  getUser(): Observable<User> {
    return this.http.get('/user').map((response) => {
      if(response['status'] === 200)
        return this.userFactory.mapPOJO(response.json());
      else
        return null;
    });
  }

}
