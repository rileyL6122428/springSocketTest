import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router'
import { ComponentModule } from './components/component.module';
import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { ROUTES_CONFIG } from './routes.config';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';

@NgModule({
  imports: [
    RouterModule.forRoot(ROUTES_CONFIG, { useHash: true }),
    ComponentModule
  ],

  declarations: [ AppComponent ],

  bootstrap: [ AppComponent ],

  providers: [
    { provide: LocationStrategy, useClass: HashLocationStrategy },
  ]
})
export class AppModule { }
