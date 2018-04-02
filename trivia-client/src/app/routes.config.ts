import { Routes } from '@angular/router';
import { MatchmakingComponent } from './components/matchmaking/matchmaking.component';
import { TriviaRoomComponent } from './components/trivia-room/trivia-room.component';

export const ROUTES_CONFIG: Routes = [
  { path: 'matchmaking', component: MatchmakingComponent, children: [
    { path: 'room/:name', component: TriviaRoomComponent }
  ]},
  { path: '', redirectTo: 'matchmaking', pathMatch: 'full' },
  { path: '**', redirectTo: 'matchmaking' }
];
