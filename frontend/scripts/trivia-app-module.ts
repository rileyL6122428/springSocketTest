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
import { TriviaServiceModule } from './services-module/service.module';


@NgModule({
  imports: [
    RouterModule.forRoot(routes, { useHash: true }),
    BrowserModule,
    FormsModule,
    HttpModule,
    TriviaServiceModule
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
  ]
})
export class TriviaAppModule { }
