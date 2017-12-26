import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ServicesModule } from '../services/service.module';
import { MatchmakingComponent } from './matchmaking/matchmaking.component';
import { RoomComponent } from './room/room.component'
import { BrowserModule } from '@angular/platform-browser';

@NgModule({
  imports: [
    ServicesModule,
    FormsModule,
    BrowserModule
  ],

  declarations: [
    MatchmakingComponent,
    RoomComponent
  ],

  entryComponents: [
    MatchmakingComponent
  ]
})
export default class ComponentModule { }
