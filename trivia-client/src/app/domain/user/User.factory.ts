import { Injectable } from  '@angular/core';
import { User } from './User';

@Injectable()
export class UserDomainFactory {

  mapPOJO(userPOJO: Object): User {
    return new User(userPOJO);
  }

}
