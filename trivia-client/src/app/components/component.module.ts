import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { ServicesModule } from '../services/service.module';
import { MatchmakingComponent } from './matchmaking/matchmaking.component';
import { RouterModule } from '@angular/router';
import { TriviaRoomComponent } from './trivia-room/trivia-room.component';
import { TriviaChatComponent } from './trivia-chat/trivia-chat.component';
import { ProfOakImgComponent } from './trivia-game/prof-oak-img/prof-oak-img.component';
import { TriviaChatModule } from './trivia-chat/trivia-chat.module';
import { TriviaGameComponent } from './trivia-game/trivia-game.component';
import { TriviaUserComponent } from './trivia-user/trivia-user.component';
import { SpeechBubbleComponent } from './trivia-game/speech-bubble/speech-bubble.component';
import { TypingAnimationDirective } from 'angular-typing-animation';
import { ProfOakComponent } from './trivia-game/prof-oak/prof-oak.component';
import { RoomButtonModule } from './room-button/room-button.module';
import { BulbasaurComponent } from './trivia-game/pokemon/bulbasaur/bulbasuar.component';
import { PokemonComponent } from './trivia-game/pokemon/pokemon.component';
import { PokemonSelectionComponent } from './trivia-game/pokemon-selection/pokemon-selection.component';
import { CharmanderComponent } from './trivia-game/pokemon/charmander/charmander.component';
import { SquirtleComponent } from './trivia-game/pokemon/squirtle/squirtle.component';
import { PokeballComponent } from './trivia-game/pokeball-two/pokeball.component';
import { PokemonAnswerComponent } from './trivia-game/pokemon-answer/pokemon-answer.component';

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
    ProfOakImgComponent,
    TriviaGameComponent,
    TriviaUserComponent,
    SpeechBubbleComponent,
    TypingAnimationDirective,
    ProfOakComponent,
    BulbasaurComponent,
    PokemonComponent,
    PokemonSelectionComponent,
    CharmanderComponent,
    SquirtleComponent,
    PokeballComponent,
    PokemonAnswerComponent
  ],

  entryComponents: [
    MatchmakingComponent
  ]
})
export class ComponentModule { }
