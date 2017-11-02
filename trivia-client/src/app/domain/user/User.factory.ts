import { Injectable } from  '@angular/core';
import { User } from './User';
@Injectable()
export class UserFactory {

  mapPOJO(userPOJO: Object): User {
    return new User(userPOJO);
  }

}
