import 'zone.js';
import 'reflect-metadata';
import { NgModule, Inject } from '@angular/core';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router'
import { Http, HttpModule } from '@angular/http';
import { BrowserModule } from '@angular/platform-browser';
import { routes } from './routes';
import { MatchmakingComponent } from './components/matchmaking.component';
import { ChatRoomComponent } from './components/chat-room.component';
import { TriviaAppComponent } from './trivia-app-component';
import { StompModule } from './stomp-module/stomp.module';
import { CookieService } from 'angular2-cookie/services/cookies.service'
import { UserService } from './services/user.service'
import { RoomService } from './services/room.service';
import { MatchmakingService } from './services/matchmaking.service';


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
    ChatRoomComponent,
    TriviaAppComponent
  ],

  entryComponents: [
    MatchmakingComponent,
    ChatRoomComponent
  ],

  bootstrap:    [ TriviaAppComponent ],
  providers: [
    { provide: LocationStrategy, useClass: HashLocationStrategy },
    CookieService,
    UserService,
    RoomService,
    MatchmakingService
  ]
})
export class TriviaAppModule { }
