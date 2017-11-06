import { TestBed, inject } from '@angular/core/testing';
import { UserFactory } from './User.factory';
import { User } from './user';

describe('UserFactory', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserFactory]
    });
  });

  describe("#mapPOJO", () => {
    it("returns a User instance with mapped properties", inject([UserFactory], (userFactory: UserFactory) => {
      let userPOJO: Object = { name: "test-name" };
      let user: User = userFactory.mapPOJO(userPOJO);

      expect(user).toEqual(jasmine.any(User));
      expect(user.name).toEqual("test-name");
    }));
  });

  describe('#mapPOJOList', () => {
    it("maps the list of user POJOs to User objects",
      inject([UserFactory], (userFactory: UserFactory) => {
        let firstUserPOJO = { name: "first-user" };
        let secondUserPOJO = { name: "second-user" };
        let thirdUserPOJO = { name: "third-user" };
        let userPOJOs = [ firstUserPOJO, secondUserPOJO, thirdUserPOJO ];

        let users: Array<User> = userFactory.mapPOJOList(userPOJOs);

        users.forEach((user, index) => {
          let userPOJO = userPOJOs[index];
          expect(user.name).toEqual(userPOJO['name']);
        });
      }
    ));
  });
});
