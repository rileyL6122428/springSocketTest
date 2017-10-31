import { Routes } from '@angular/router';
import { MatchmakingComponent } from './matchmaking/matchmaking.component';

export const ROUTES_CONFIG: Routes = [
  { path: 'home', component: MatchmakingComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', redirectTo: 'home' },
];
