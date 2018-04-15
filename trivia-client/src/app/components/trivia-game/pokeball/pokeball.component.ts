import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'pokeball',
  templateUrl: './pokeball.component.html',
  styleUrls: ['./pokeball.component.scss']
})
export class PokeballComponent {

  private selected: boolean;

  select(): void {
    this.selected = true;
  }

 }
