import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'pokeball-two',
  templateUrl: './pokeball-two.component.html',
  styleUrls: ['./pokeball-two.component.scss']
})
export class PokeballTwoComponent {

  private isOpen: boolean;

  private baseline: number;

  private topOpeningHeight: number;
  private topOpeningHeightDelta: number;

  private bottomOpeningHeight: number;
  private bottomOpeningHeightDelta: number;

  private buttonHeight: number;
  private buttonHeightDelta: number;

  private buttonYRadius: number;
  private buttonYRadiusDelta: number;

  constructor() {
    this.baseline = 120;
    this.topOpeningHeight = this.baseline;
    this.bottomOpeningHeight = this.baseline;
    this.buttonHeight = this.baseline;
    this.buttonYRadius = 25;

    this.topOpeningHeightDelta = .8;
    this.bottomOpeningHeightDelta = 1.15;
    this.buttonHeightDelta = .82;
    this.buttonYRadiusDelta = .09;
  }

  animate(): void {
    (this.isOpen) ? this.animateClose() : this.animateOpen();
    this.isOpen = !this.isOpen;
  }

  get outerCircleTopD(): string {
    return `
        M
          10 ${this.baseline}
        C
          10 ${this.baseline - 150}
          210 ${this.baseline - 150}
          210 ${this.baseline}
      `;
  }

  get outerCircleBottomD(): string {
    return `
      M
        10 ${this.baseline}
      C
        10 ${this.baseline + 150}
        210 ${this.baseline + 150}
        210 ${this.baseline}
    `;
  }

  get innerCircleTopD(): string {
    return `
      M
        10 ${this.baseline}
      C
        10 ${this.topOpeningHeight}
        210 ${this.topOpeningHeight}
        210 ${this.baseline}
    `;
  }

  get innerCircleBottomD(): string {
    return `
      M
        10 ${this.baseline}
      C
        10 ${this.bottomOpeningHeight}
        210 ${this.bottomOpeningHeight}
        210 ${this.baseline}
    `;
  }

  get buttonRadiusY(): string {
    return `${this.buttonYRadius}`;
  }

  get buttonY(): string {
    return `${this.buttonHeight}`;
  }

  private animateOpen(): void {
    const openIntervalId = setInterval(() => {
      if (this.bottomOpeningHeight > this.baseline - 100) {
        this.open();
      } else {
        clearInterval(openIntervalId);
      }
    }, 5);
  }

  private animateClose(): void {
    const closeIntervalId = setInterval(() => {
      if (this.bottomOpeningHeight !== this.baseline) {
        this.close();
      } else {
        clearInterval(closeIntervalId);
      }
    }, 4);
  }

  open(): void {
    this.topOpeningHeight += this.topOpeningHeightDelta;
    this.bottomOpeningHeight -= this.bottomOpeningHeightDelta;
    this.buttonHeight -= this.buttonHeightDelta;
    this.buttonYRadius -= this.buttonYRadiusDelta;
  }

  close(): void {
    this.topOpeningHeight -= this.topOpeningHeightDelta;
    this.bottomOpeningHeight += this.bottomOpeningHeightDelta;
    this.buttonHeight += this.buttonHeightDelta;
    this.buttonYRadius += this.buttonYRadiusDelta;
  }

}
