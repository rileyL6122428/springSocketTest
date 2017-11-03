import { TestBed, inject } from '@angular/core/testing';
import { HttpModule, XHRBackend, ResponseOptions, Connection, Response } from '@angular/http';
import { MockBackend, MockConnection } from '@angular/http/testing';
import { UserService } from './user.service';
import { Subscription } from 'rxjs/subscription';
import { User } from '../domain/user/User';

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
    it("makes an http GET request to '/user'",
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

    it("returns an observable that passes null upon an unsucessful request",
      inject([UserService, XHRBackend], (userService, mockBackend) => {

        mockBackend.connections.subscribe((connection) => {
          connection.mockRespond(new Response(new ResponseOptions({
            status: 400,
            body: JSON.stringify({ message: "AN_ERROR_MESSAGE" }),
          })));
        });

        userService.getUser().subscribe((user: User) => {
          expect(user).toBeNull();
        });
      })
    );
  });
});
