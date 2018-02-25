import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { ServicesModule } from '../services/service.module';
import { MatchmakingComponent } from './matchmaking/matchmaking.component';
import { RoomComponent } from './room/room.component'
import { GameComponent } from './game/game.component';
import { ChatComponent } from './chat/chat.component';
import { ChatMessageComponent } from './chat-message/chat-message.component';
import { CharizardComponent } from './charizard/charizard.component';

@NgModule({
  imports: [
    ServicesModule,
    FormsModule,
    BrowserModule
  ],

  declarations: [
    MatchmakingComponent,
    RoomComponent,
    GameComponent,
    ChatComponent,
    ChatMessageComponent,
    CharizardComponent
  ],

  entryComponents: [
    MatchmakingComponent
  ]
})
export class ComponentModule { }
