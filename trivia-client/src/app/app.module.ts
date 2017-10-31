import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router'
import { AppComponent } from './app.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { ROUTES_CONFIG } from './routes.config';
import { MatchmakingComponent } from './matchmaking/matchmaking.component';

import { StompService, StompConfig } from '@stomp/ng2-stompjs';
import { STOMP_CONFIG } from './stomp.config';

@NgModule({
  imports: [
    BrowserModule,
    RouterModule.forRoot(ROUTES_CONFIG, { useHash: true })
  ],

  declarations: [
    AppComponent,
    MatchmakingComponent
  ],

  entryComponents: [
    MatchmakingComponent
  ],

  bootstrap: [AppComponent],

  providers: [
    { provide: LocationStrategy, useClass: HashLocationStrategy },
    StompService,
    {
      provide: StompConfig,
      useValue: STOMP_CONFIG
    }
  ],
})
export class AppModule { }
