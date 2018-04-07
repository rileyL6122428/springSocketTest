import { TestBed, inject } from '@angular/core/testing';
import { HttpModule, XHRBackend, ResponseOptions, Connection, Response } from '@angular/http';
import { MockBackend, MockConnection } from '@angular/http/testing';
import { MatchmakingStream } from './matchmaking.service';
import { User } from '../domain/user/user';
import { DomainFactoryModule } from '../domain/factory.module';
import { MatchmakingStats } from '../domain/matchmaking/matchmaking-stats';
import { MatchmakingStatsFactory } from '../domain/matchmaking/matchmaking-stats.factory';
import { StompService, StompConfig } from '@stomp/ng2-stompjs';
import { STOMP_CONFIG } from '../stomp.config';
import { stubableObservable, StubableStompService } from '../test-utils/mocks';
import { CookieService } from 'angular2-cookie/services/cookies.service'
import { Observable } from 'rxjs/Observable';
import { Observer } from 'rxjs/Observer';
import { Subscription } from 'rxjs/Subscription';

describe('MatchmakingService', () => {

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpModule, DomainFactoryModule ],
      providers: [
        MatchmakingStream,
        { provide: XHRBackend, useClass: MockBackend },
        { provide: StompConfig, useValue: STOMP_CONFIG },
        { provide: StompService, useClass: StubableStompService },
        CookieService
      ]
    });
  });

  describe('#getMatchmakingStats', () => {
    it("makes a GET request to '/matchmaking/stats'",
      inject([MatchmakingStream, XHRBackend], (matchmakingService: MatchmakingStream, mockBackend: MockBackend) => {
        let getMatchmakingStatsConnection: any = undefined;
        mockBackend.connections.subscribe((connection) => {
          getMatchmakingStatsConnection = connection;
        });

        matchmakingService.getMatchmakingStats();

        expect(getMatchmakingStatsConnection).toBeDefined();
        expect(getMatchmakingStatsConnection.request.url).toEqual("/matchmaking/stats");
      }
    ));

    it("returns an observable that delegates to matchmakingStatsFactory to map successful requests",
      inject([MatchmakingStream, MatchmakingStatsFactory, XHRBackend], (matchmakingService: MatchmakingStream, matchmakingStatsFactory: MatchmakingStatsFactory, mockBackend: MockBackend) => {
        mockBackend.connections.subscribe((connection) => {
          connection.mockRespond(new Response(new ResponseOptions({
            status: 200,
            body: JSON.stringify({ message: "A_SUCCESSFUL_MOCK_RESPONSE_BODY" }),
          })));
        });

        let mockMappedResponse: any = {};
        spyOn(matchmakingStatsFactory, 'createNewStats').and.returnValue(mockMappedResponse);

        matchmakingService.getMatchmakingStats().subscribe((matchmakingStats: MatchmakingStats) => {
          expect(matchmakingStatsFactory.createNewStats).toHaveBeenCalledWith({ message: "A_SUCCESSFUL_MOCK_RESPONSE_BODY" });
          expect(matchmakingStats).toBe(mockMappedResponse);
        });
      }
    ));

    it("returns an observable that passes null upon an unsucessful request",
      inject([MatchmakingStream, XHRBackend], (matchmakingService: MatchmakingStream, mockBackend: MockBackend) => {
        mockBackend.connections.subscribe((connection) => {
          connection.mockRespond(new Response(new ResponseOptions({
            status: 400,
            body: JSON.stringify({ message: "AN_UNSUCCESSFUL_MOCK_RESPONSE_BODY" }),
          })));
        });

        matchmakingService.getMatchmakingStats().subscribe((matchmakingStats: MatchmakingStats) => {
          expect(matchmakingStats).toBe(null);
        });
      }
    ));
  });

  describe("#subscribeToMatchmaking", () => {
    it("delegates to StompService#subscribe, passing along the appropriate path and headers", inject([MatchmakingStream, StompService], (matchmakingService, stompService) => {
      document.cookie = "TRIVIA_SESSION_COOKIE=EXAMPLE_SESSION_COOKIE_VALUE";
      spyOn(stompService, "subscribe").and.returnValue(stubableObservable());
      matchmakingService.subscribeToMatchmaking();
      expect(stompService.subscribe).toHaveBeenCalledWith("/topic/matchmaking", { SESSION_ID: "EXAMPLE_SESSION_COOKIE_VALUE" });
    }));

    it("maps the returned message by passing it to the matchmaking stats factory",
      inject([MatchmakingStream, StompService, MatchmakingStatsFactory], (matchmakingService, stompService, statsFactory) => {
        let messageBody = JSON.stringify({mockStatsPayload:'mockStatsPayload'});
        spyOn(stompService, "subscribe").and.returnValue(Observable.create(
          (observer: Observer<any>) => observer.next({ body: messageBody })
        ));

        let matchmakingStats: MatchmakingStats = new MatchmakingStats({});
        spyOn(statsFactory, "createNewStats").and.returnValue(matchmakingStats);

        matchmakingService.subscribeToMatchmaking().subscribe((observedStats) => {
          expect(statsFactory.createNewStats).toHaveBeenCalledWith({ mockStatsPayload: 'mockStatsPayload' });
          expect(observedStats).toBe(matchmakingStats);
        });
      }
    ));
  });

});
