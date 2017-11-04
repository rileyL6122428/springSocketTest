import { TestBed, inject } from '@angular/core/testing';
import { UserDomainFactory } from './User.factory';
import { User } from './User';

describe('UserDomainFactory', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserDomainFactory]
    });
  });

  describe("#mapPOJO", () => {
    it("returns a User instance with mapped properties", inject([UserDomainFactory], (userFactory: UserDomainFactory) => {
      let userPOJO: Object = { name: "test-name" };
      let user: User = userFactory.mapPOJO(userPOJO);

      expect(user).toEqual(jasmine.any(User));
      expect(user.name).toEqual("test-name");
    }));
  });
});
