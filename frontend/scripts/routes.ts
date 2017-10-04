import { Routes } from '@angular/router';
import { MatchmakingComponent } from './components/matchmaking.component';
import { ChatRoomComponent } from './components/chat-room.component';

export const routes: Routes = [
  { path: 'home', component: MatchmakingComponent },
  { path: 'chat', component: ChatRoomComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', redirectTo: 'home' }
];
