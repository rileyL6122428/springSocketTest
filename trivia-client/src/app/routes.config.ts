import { Routes } from '@angular/router';
import { MatchmakingComponent } from './matchmaking/matchmaking.component';

export const ROUTES_CONFIG: Routes = [
  { path: 'matchmaking', component: MatchmakingComponent },
  { path: '', redirectTo: 'matchmaking', pathMatch: 'full' },
  { path: '**', redirectTo: 'matchmaking' },
];
