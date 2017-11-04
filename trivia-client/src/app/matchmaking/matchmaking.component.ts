import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { User } from '../domain/user/User';

@Component({
  selector: 'app-matchmaking',
  templateUrl: './matchmaking.component.html',
  styleUrls: ['./matchmaking.component.css']
})
export class MatchmakingComponent implements OnInit {

  private user: User;

  constructor(
    private userService: UserService
  ) { }

  ngOnInit() {
    debugger
    this.userService.getUser().subscribe((user: User) => {
      this.user = user;
    });
  }

}
