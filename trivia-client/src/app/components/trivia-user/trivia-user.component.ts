import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user/user.service';
import { User } from '../../domain/user/user';
import { trigger, state, style, animate, transition } from '@angular/animations';

@Component({
  selector: 'trivia-user',
  templateUrl: './trivia-user.component.html',
  styleUrls: ['./trivia-user.component.scss'],
  animations: [
    trigger('dropIn', [
      state('dropIn', style({ transform: 'translateY(0)' })),
      transition(':enter', [
        style({ transform: 'translateY(-150%)' }),
        animate('1s 3s')
      ])
    ])
  ]
})
export class TriviaUserComponent implements OnInit {

  private user: User;

  constructor(
    private userService: UserService
  ) { }

  ngOnInit() {
    this.userService.getUser().subscribe((user: User) => {
      this.user = user;
    });
  }

}
