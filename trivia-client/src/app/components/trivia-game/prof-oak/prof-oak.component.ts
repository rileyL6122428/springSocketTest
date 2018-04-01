import { Component, Input } from '@angular/core';

@Component({
  selector: 'prof-oak',
  templateUrl: './prof-oak.component.html',
  styleUrls: ['./prof-oak.component.scss']
})
export class ProfOakComponent {

  @Input() canSpeak: boolean = false;

}
