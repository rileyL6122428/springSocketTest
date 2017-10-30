import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { User } from '../domain/User';

@Injectable()
export class UserService {

  private user: User;

  constructor(
    private http: Http
  ) { }

  public getUser(params: { success: Function }): void {
    let requestSubscription = this.http.get("/user").subscribe((response) => {
      if(response['status'] === 200) {
        let userPOJO: Object = response.json();
        let user: User = User.fromPOJO(userPOJO);
        params.success(user);
      }

      requestSubscription.unsubscribe();
    });
  }

}
