import { TestBed, inject } from '@angular/core/testing';
import { HttpModule, XHRBackend, ResponseOptions } from '@angular/http';
import { MockBackend } from '@angular/http/testing';
import { UserService } from './user.service';
describe('UserService', () => {

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpModule],
      providers: [
        UserService,
        { provide: XHRBackend, useClass: MockBackend }
      ]
    });
  });

  describe("#getUser", () => {
    it("make an http GET request to '/user'",
      inject([UserService, XHRBackend], (userService, mockBackend) => {
        let getUserConnection: any = undefined;
        mockBackend.connections.subscribe((connection) => {
          getUserConnection = connection;
        });

        userService.getUser();

        expect(getUserConnection).toBeDefined();
        expect(getUserConnection.request.url).toEqual('/user');
      })
    );
  });
});
