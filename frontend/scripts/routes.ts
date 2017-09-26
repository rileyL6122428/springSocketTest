import { Routes } from '@angular/router';
import { HelloWorldComponent } from './components/hello-world.component';

export const routes: Routes = [
  { path: 'home', component: HelloWorldComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', redirectTo: 'home' }
];
