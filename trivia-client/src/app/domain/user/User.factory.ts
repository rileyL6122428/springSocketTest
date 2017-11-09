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

  mapPOJOMap(userPOJOs: object): Map<string, User> {
    let userMap: Map<string, User> = new Map<string, User>();

    Object.keys(userPOJOs).forEach((username: string) => {
      userMap.set(username, new User(userPOJOs[username]));
    });

    return userMap;
  }

}
