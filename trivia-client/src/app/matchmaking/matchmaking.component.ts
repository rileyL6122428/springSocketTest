import { Component, OnInit } from '@angular/core';
import { StompService } from '@stomp/ng2-stompjs';

@Component({
  selector: 'app-matchmaking',
  templateUrl: './matchmaking.component.html',
  styleUrls: ['./matchmaking.component.css']
})
export class MatchmakingComponent implements OnInit {

  // constructor(
  //   private stompService: StompService
  // ) { }

  ngOnInit() {
  }

}
