import { Injectable } from  '@angular/core';
import { User } from './user';

@Injectable()
export class UserFactory {

  mapPOJO(userPOJO: Object): User {
    return new User(userPOJO);
  }

  mapPOJOList(userPOJOs: Array<Object>): Array<User> {
    return userPOJOs.map((userPOJO) => {
      return this.mapPOJO(userPOJO);
    });
  }

}
