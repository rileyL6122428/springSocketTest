import 'zone.js';
import 'reflect-metadata';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { RouterModule } from '@angular/router'

import { Http, HttpModule } from '@angular/http';

import { routes } from './routes';

import { MatchmakingComponent } from './components/matchmaking.component';
import { TriviaAppComponent } from './trivia-app-component';

// import { stompConfig } from './constants/stomp.config';
// import { StompService, StompConfig } from '@stomp/ng2-stompjs';
import { StompModule } from './stomp-module/stomp.module';

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { useHash: true }),
    BrowserModule,
    FormsModule,
    HttpModule,
    StompModule
  ],
  declarations: [
    MatchmakingComponent,
    TriviaAppComponent
  ],

  entryComponents: [
    MatchmakingComponent
  ],

  bootstrap:    [ TriviaAppComponent ],
  providers: [
    { provide: LocationStrategy, useClass: HashLocationStrategy },
    // StompService,
    // { provide: StompConfig, useValue: stompConfig }
  ]
})
export class TriviaAppModule { }
