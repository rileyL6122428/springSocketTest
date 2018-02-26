import { Component, OnInit, Input } from '@angular/core';
import { Pokemon } from '../../../domain/pokemon/pokemon';

@Component({
  selector: 'pokemon-room-icon',
  templateUrl: './pokemon-room-icon.component.html',
  styleUrls: ['./pokemon-room-icon.component.css']
})
export class PokemonRoomIconComponent implements OnInit {

  @Input() pokemon: Pokemon;

  constructor() { }

  ngOnInit() {

  }

}
