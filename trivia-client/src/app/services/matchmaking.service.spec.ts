import { TestBed, inject } from '@angular/core/testing';
import { HttpModule, XHRBackend, ResponseOptions, Connection, Response } from '@angular/http';
import { MockBackend, MockConnection } from '@angular/http/testing';
import { MatchmakingService } from './matchmaking.service';
import { Subscription } from 'rxjs/subscription';
import { User } from '../domain/user/user';
import { DomainFactoryModule } from '../domain/factory.module';
import { MatchmakingStats } from '../domain/matchmaking/matchmaking-stats';
import { Observable } from 'rxjs/observable';
import { MatchmakingStatsFactory } from '../domain/matchmaking/matchmaking-stats.factory';
import { StompService, StompConfig } from '@stomp/ng2-stompjs';
import { STOMP_CONFIG } from '../stomp.config';

describe('MatchmakingService', () => {

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpModule, DomainFactoryModule ],
      providers: [
        MatchmakingService,
        { provide: XHRBackend, useClass: MockBackend },
        { provide: StompConfig, useValue: STOMP_CONFIG },
        StompService
      ]
    });
  });

  describe('#getMatchmakingStats', () => {
    it("makes a GET request to '/matchmaking/stats'",
      inject([MatchmakingService, XHRBackend], (matchmakingService: MatchmakingService, mockBackend: MockBackend) => {
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
      inject([MatchmakingService, MatchmakingStatsFactory, XHRBackend], (matchmakingService: MatchmakingService, matchmakingStatsFactory: MatchmakingStatsFactory, mockBackend: MockBackend) => {
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
      inject([MatchmakingService, XHRBackend], (matchmakingService: MatchmakingService, mockBackend: MockBackend) => {
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
    xit("delegates to StompService#subscribe, passing along the appropriate path and headers", inject([MatchmakingService, StompService], (matchmakingService, stompService) => {
      spyOn(stompService, "subscribe");
      matchmakingService.subscribeToMatchmaking();
      expect(stompService.subscribe).toHaveBeenCalled();
    }));
  });

});
