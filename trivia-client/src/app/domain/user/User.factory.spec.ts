import { TestBed, inject } from '@angular/core/testing';
import { UserFactory } from './User.factory';
import { User } from './User';

describe('TestServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserFactory]
    });
  });

  it('should be created', inject([UserFactory], (userFactory: UserFactory) => {
    expect(userFactory).toBeTruthy();
  }));

  describe("#mapPOJO", () => {
    it("returns a User instance with mapped properties", inject([UserFactory], (userFactory: UserFactory) => {
      let userPOJO: Object = { name: "test-name" };
      let user: User = userFactory.mapPOJO(userPOJO);

      expect(user).toEqual(jasmine.any(User));
      expect(user.name).toEqual("test-name");
    }));
  });
});
