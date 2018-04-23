import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'pokeball-two',
  templateUrl: './pokeball-two.component.html',
  styleUrls: ['./pokeball-two.component.scss']
})
export class PokeballTwoComponent {

  @Output() selection: EventEmitter<void> = new EventEmitter<void>();

  private isOpen: boolean;

  private baselineX: number;
  private baselineY: number;

  private topOpeningHeight: number;
  private topOpeningHeightDelta: number;

  private bottomOpeningHeight: number;
  private bottomOpeningHeightDelta: number;

  private buttonHeight: number;
  private buttonHeightDelta: number;

  private buttonYRadius: number;
  private buttonYRadiusDelta: number;

  private radiusX: number;
  private radiusY: number;

  private height: number;
  private width: number;

  private _strokeWidth: number;

  constructor() {
    this.baselineX = 55;
    this.baselineY = 60;
    this.topOpeningHeight = this.baselineY;
    this.bottomOpeningHeight = this.baselineY;
    this.buttonHeight = this.baselineY;
    this.buttonYRadius = 12.5;

    this.topOpeningHeightDelta = .4;
    this.bottomOpeningHeightDelta = .575;
    this.buttonHeightDelta = .41;
    this.buttonYRadiusDelta = .045;

    this._strokeWidth = 5;
    this.radiusX = 50;
    this.radiusY = 75;

    this.width = 110;
    this.height = 120;
  }

  animate(): void {
    (this.isOpen) ? this.animateClose() : this.animateOpen();
    this.isOpen = !this.isOpen;
  }

  get circleTopD(): string {
    return `
      M
          ${this.baselineX - this.radiusX} ${this.baselineY}
        C
          ${this.baselineX - this.radiusX} ${this.baselineY - this.radiusY}
          ${this.baselineX + this.radiusX} ${this.baselineY - this.radiusY}
          ${this.baselineX + this.radiusX} ${this.baselineY}

        C
          ${this.baselineX + this.radiusX} ${this.bottomOpeningHeight}
          ${this.baselineX - this.radiusX} ${this.bottomOpeningHeight}
          ${this.baselineX - this.radiusX} ${this.baselineY}
          `;
  }

  get circleBottomD(): string {
    return `
      M
        5 60
      C
        ${this.baselineX - this.radiusX} ${this.baselineY + this.radiusY}
        ${this.baselineX + this.radiusX} ${this.baselineY + this.radiusY}
        ${this.baselineX + this.radiusX} ${this.baselineY}
      C
        ${this.baselineX + this.radiusX} ${this.topOpeningHeight}
        ${this.baselineX - this.radiusX} ${this.topOpeningHeight}
        ${this.baselineX - this.radiusX} ${this.baselineY}
    `;
  }

  get outerCircleBottomD(): string {
    return `
      M
        ${this.baselineX - this.radiusX} ${this.baselineY}
      C
        ${this.baselineX - this.radiusX} ${this.baselineY + this.radiusY}
        ${this.baselineX + this.radiusX} ${this.baselineY + this.radiusY}
        ${this.baselineX + this.radiusX} ${this.baselineY}
    `;
  }

  get outerCircleTopD(): string {
    return `
        M
          ${this.baselineX - this.radiusX} ${this.baselineY}
        C
          ${this.baselineX - this.radiusX} ${this.baselineY - this.radiusY}
          ${this.baselineX + this.radiusX} ${this.baselineY - this.radiusY}
          ${this.baselineX + this.radiusX} ${this.baselineY}
      `;
  }


  get innerCircleTopD(): string {
    return `
      M
        ${this.baselineX - this.radiusX} ${this.baselineY}
      C
        ${this.baselineX - this.radiusX} ${this.topOpeningHeight}
        ${this.baselineX + this.radiusX} ${this.topOpeningHeight}
        ${this.baselineX + this.radiusX} ${this.baselineY}
    `;
  }

  get innerCircleBottomD(): string {
    return `
      M
        ${this.baselineX - this.radiusX} ${this.baselineY}
      C
        ${this.baselineX - this.radiusX} ${this.bottomOpeningHeight}
        ${this.baselineX + this.radiusX} ${this.bottomOpeningHeight}
        ${this.baselineX + this.radiusX} ${this.baselineY}
    `;
  }

  get buttonRadiusY(): string {
    return `${this.buttonYRadius}`;
  }

  get buttonY(): string {
    return `${this.buttonHeight}`;
  }

  get strokeWidth(): string {
    return `${this._strokeWidth}`;
  }

  private animateOpen(): void {
    const openIntervalId = setInterval(() => {
      if (this.bottomOpeningHeight > this.baselineY - 50) {
        this.open();
      } else {
        clearInterval(openIntervalId);
      }
    }, 5);

    setTimeout(() => this.animateClose(), 1200);
  }

  private animateClose(): void {
    const closeIntervalId = setInterval(() => {
      if (this.bottomOpeningHeight !== this.baselineY) {
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
