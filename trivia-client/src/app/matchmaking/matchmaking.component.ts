import { Component, OnInit } from '@angular/core';
import { StompServiceFacade } from '../stomp-module/services/stomp.service.facade';

@Component({
  selector: 'app-matchmaking',
  templateUrl: './matchmaking.component.html',
  styleUrls: ['./matchmaking.component.css']
})
export class MatchmakingComponent implements OnInit {

  constructor(
    private stompService: StompServiceFacade
  ) { }

  ngOnInit() {
  }

}
