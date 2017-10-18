import { Injectable } from '@angular/core';
import { User } from '../domain/User';

@Injectable()
export class UserService {

  private user: User;

  public storeUser(user: User): void {
    this.user = user;
  }

  public getUser(): User {
    return this.user;
  }

}
