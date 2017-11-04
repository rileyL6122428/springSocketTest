import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/observable';
import 'rxjs/add/operator/map'
import { User } from '../domain/user/User';
import { UserDomainFactory } from '../domain/user/User.factory';

@Injectable()
export class UserService {

  constructor(
    private http: Http,
    private userDomainFactory: UserDomainFactory
  ) { }

  getUser(): Observable<User> {
    return this.http.get('/user').map((response) => {
      if(response['status'] === 200)
        return this.userDomainFactory.mapPOJO(response.json());
      else
        return null;
    });
  }

}
