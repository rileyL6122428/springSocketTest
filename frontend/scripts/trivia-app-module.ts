import 'zone.js';
import 'reflect-metadata';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { RouterModule } from '@angular/router'

import { Http } from '@angular/http';

import { HttpModule } from '@angular/http';

import { routes } from './routes';

import { HelloWorldComponent } from './components/hello-world.component';
import { TriviaAppComponent } from './trivia-app-component';

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { useHash: true }),
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  declarations: [
    HelloWorldComponent,
    TriviaAppComponent
  ],

  entryComponents: [
    HelloWorldComponent
  ],

  bootstrap:    [ TriviaAppComponent ],
  providers: [
    { provide: LocationStrategy, useClass: HashLocationStrategy}
  ]
})
export class TriviaAppModule { }
