import { Routes } from '@angular/router';
import { MatchmakingComponent } from './components/matchmaking/matchmaking.component';
import { RoomComponent } from './components/room/room.component';

export const ROUTES_CONFIG: Routes = [
  { path: 'matchmaking', component: MatchmakingComponent },
  { path: 'room/:name', component: RoomComponent },
  { path: '', redirectTo: 'matchmaking', pathMatch: 'full' },
  { path: '**', redirectTo: 'matchmaking' },
];
