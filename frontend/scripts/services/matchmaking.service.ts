import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class MatchmakingService {

  constructor(
    private http: Http
  ) {

  }

  public getMatchmakingStats(params: { success: Function }): void {
    let requestSubscription = this.http.get("/matchmaking/stats").subscribe((response) => {
      if(response['status'] === 200) {
        let responseBody: Object =  response.json();
        params.success(responseBody);
      }

      requestSubscription.unsubscribe();
    });
  }

}
