import { Routes } from '@angular/router';
import { MatchmakingComponent } from './matchmaking/matchmaking.component';
import { RoomComponent } from './room/room.component';

export const ROUTES_CONFIG: Routes = [
  { path: 'matchmaking', component: MatchmakingComponent },
  { path: 'room/:name', component: RoomComponent },
  { path: '', redirectTo: 'matchmaking', pathMatch: 'full' },
  { path: '**', redirectTo: 'matchmaking' },
];
