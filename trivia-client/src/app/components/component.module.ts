import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { ServicesModule } from '../services/service.module';
import { MatchmakingComponent } from './matchmaking/matchmaking.component';
import { RouterModule } from '@angular/router';
import { TriviaRoomComponent } from './trivia-room/trivia-room.component';
import { TriviaChatComponent } from './trivia-chat/trivia-chat.component';
import { QuarterArcComponent } from './vectors/quarter-arc/quarter-arc.component';
import { ProfOakImgComponent } from './trivia-game/prof-oak-img/prof-oak-img.component';
import { TriviaChatModule } from './trivia-chat/trivia-chat.module';
import { TriviaGameComponent } from './trivia-game/trivia-game.component';
import { TriviaUserComponent } from './trivia-user/trivia-user.component';
import { SpeechBubbleComponent } from './trivia-game/speech-bubble/speech-bubble.component';
import { TypingAnimationDirective } from 'angular-typing-animation';
import { ProfOakComponent } from './trivia-game/prof-oak/prof-oak.component';
import { RoomButtonModule } from './room-button/room-button.module';
import { BulbasaurComponent } from './trivia-game/pokemon/bulbasaur/bulbasuar.component';

@NgModule({
  imports: [
    ServicesModule,
    FormsModule,
    BrowserModule,
    RouterModule,
    TriviaChatModule,
    RoomButtonModule
  ],

  declarations: [
    MatchmakingComponent,
    TriviaRoomComponent,
    QuarterArcComponent,
    ProfOakImgComponent,
    TriviaGameComponent,
    TriviaUserComponent,
    SpeechBubbleComponent,
    TypingAnimationDirective,
    ProfOakComponent,
    BulbasaurComponent
  ],

  entryComponents: [
    MatchmakingComponent
  ]
})
export class ComponentModule { }
