import { Routes } from '@angular/router';
import { MatchmakingComponent } from './matchmaking/matchmaking.component';

export const routes: Routes = [
  { path: 'home', component: MatchmakingComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', redirectTo: 'home' },
];
