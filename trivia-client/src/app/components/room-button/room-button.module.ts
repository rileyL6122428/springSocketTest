import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoomButtonComponent } from './join-room-button.component';
import { PokemonRoomIconComponent } from './pokemon-icons/pokemon-room-icon/pokemon-room-icon.component';
import { BlazikenComponent } from './pokemon-icons/blaziken/blaziken.component';
import { CharizardComponent } from './pokemon-icons/charizard/charizard.component';
import { EeveeComponent } from './pokemon-icons/eevee/eevee.component';
import { GarchompComponent } from './pokemon-icons/garchomp/garchomp.component';
import { GengarComponent } from './pokemon-icons/gengar/gengar.component';
import { LucarioComponent } from './pokemon-icons/lucario/lucario.component';
import { LugiaComponent } from './pokemon-icons/lugia/lugia.component';
import { MewtwoComponent } from './pokemon-icons/mewtwo/mewtwo.component';
import { PikachuComponent } from './pokemon-icons/pikachu/pikachu.component';
import { RayquazaComponent } from './pokemon-icons/rayquaza/rayquaza.component';

@NgModule({
  imports: [ CommonModule ],
  exports: [ RoomButtonComponent ],
  declarations: [
    RoomButtonComponent,
    PokemonRoomIconComponent,
    BlazikenComponent,
    CharizardComponent,
    EeveeComponent,
    GarchompComponent,
    GengarComponent,
    LucarioComponent,
    LugiaComponent,
    MewtwoComponent,
    PikachuComponent,
    RayquazaComponent
  ]
})
export class RoomButtonModule { }
