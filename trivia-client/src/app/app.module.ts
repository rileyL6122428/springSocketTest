import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router'
import { AppComponent } from './app.component';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { routes } from './routes';
import { MatchmakingComponent } from './matchmaking/matchmaking.component';
import { StompModule } from './stomp-module/stomp.module';

@NgModule({
  imports: [
    BrowserModule,
    StompModule,
    RouterModule.forRoot(routes, { useHash: true })
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
    { provide: LocationStrategy, useClass: HashLocationStrategy }
  ],
})
export class AppModule { }
