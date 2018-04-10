import { Component, OnInit, Input, OnChanges, SimpleChanges, SimpleChange } from '@angular/core';

@Component({
  selector: 'speech-bubble',
  templateUrl: './speech-bubble.component.html',
  styleUrls: ['./speech-bubble.component.scss']
})
export class SpeechBubbleComponent implements OnChanges {

  @Input() displayable: boolean;
  @Input() text: string;
  private textCleared: boolean = false;

  ngOnChanges(changes: SimpleChanges) {
    if (this.textChanged(changes)) {
      this.clearText();
    }
  }

  private textChanged(changes: SimpleChanges): boolean {
    const textChange: SimpleChange = changes['text'];
    return textChange && textChange.currentValue !== textChange.previousValue;
  }

  private clearText(): void {
    this.textCleared = true;
    setTimeout(() => this.textCleared = false, 100);
  }

}
