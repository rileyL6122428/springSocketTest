import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { ServicesModule } from '../services/service.module';
import { MatchmakingComponent } from './matchmaking/matchmaking.component';
import { RoomComponent } from './room/room.component';
import { GameComponent } from './game/game.component';
import { CharizardComponent } from './pokemon-icons/charizard/charizard.component';
import { GarchompComponent } from './pokemon-icons/garchomp/garchomp.component';
import { EeveeComponent } from './pokemon-icons/eevee/eevee.component';
import { MewtwoComponent } from './pokemon-icons/mewtwo/mewtwo.component';
import { LugiaComponent } from './pokemon-icons/lugia/lugia.component';
import { GengarComponent } from './pokemon-icons/gengar/gengar.component';
import { RayquazaComponent } from './pokemon-icons/rayquaza/rayquaza.component';
import { BlazikenComponent } from './pokemon-icons/blaziken/blaziken.component';
import { PikachuComponent } from './pokemon-icons/pikachu/pikachu.component';
import { LucarioComponent } from './pokemon-icons/lucario/lucario.component';
import { PokemonRoomIconComponent } from './pokemon-icons/pokemon-room-icon/pokemon-room-icon.component';
import { RoomButtonComponent } from './room-button/join-room-button.component';
import { RouterModule } from '@angular/router';
import { TriviaRoomComponent } from './trivia-room/trivia-room.component';
import { TriviaChatComponent } from './trivia-chat/trivia-chat.component';
import { QuarterArcComponent } from './vectors/quarter-arc/quarter-arc.component';
import { ProfOakComponent } from './prof-oak/prof-oak.component';
import { TriviaChatModule } from './trivia-chat/trivia-chat.module';
import { TriviaGameComponent } from './trivia-game/trivia-game.component';
import { TriviaUserComponent } from './trivia-user/trivia-user.component';

@NgModule({
  imports: [
    ServicesModule,
    FormsModule,
    BrowserModule,
    RouterModule,
    TriviaChatModule
  ],

  declarations: [
    MatchmakingComponent,
    RoomComponent,
    GameComponent,
    CharizardComponent,
    GarchompComponent,
    EeveeComponent,
    MewtwoComponent,
    LugiaComponent,
    GengarComponent,
    RayquazaComponent,
    BlazikenComponent,
    PikachuComponent,
    LucarioComponent,
    PokemonRoomIconComponent,
    RoomButtonComponent,
    TriviaRoomComponent,
    QuarterArcComponent,
    ProfOakComponent,
    TriviaGameComponent,
    TriviaUserComponent
  ],

  entryComponents: [
    MatchmakingComponent
  ]
})
export class ComponentModule { }
