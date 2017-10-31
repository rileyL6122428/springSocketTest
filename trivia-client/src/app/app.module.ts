import { NgModule } from '@angular/core';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router'

import { AppComponent } from './app.component';
import { MatchmakingComponent } from './matchmaking/matchmaking.component';

import { StompService, StompConfig } from '@stomp/ng2-stompjs';

import { ROUTES_CONFIG } from './routes.config';
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

  bootstrap: [ AppComponent ],

  providers: [
    { provide: LocationStrategy, useClass: HashLocationStrategy },
    { provide: StompConfig, useValue: STOMP_CONFIG },
    StompService,
  ],
})
export class AppModule { }
